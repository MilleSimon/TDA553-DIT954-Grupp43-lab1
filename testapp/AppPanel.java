import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class AppPanel extends JPanel {
    private Position position;
    private Car car;
    private ArrayList<Car> cars = new ArrayList<>();
    private int gasAmount;
    private int brakeAmount;
    private volatile boolean[] directionInput = {false,false,false,false}; // Up, Down, Left, Right - UDLR Format (Boolean Movement Controls)
    private final int scalingFactor = 15; // The car class's coordinates are not tuned for a 1920x1080 game surface, so coordinate scaling is needed to not have uncontrollable supersonic cars
    private final int width = 1920;
    private final int height = 1080;
    InputMap inputMap = this.getInputMap(WHEN_IN_FOCUSED_WINDOW);
    ActionMap actionMap = this.getActionMap();

    public AppPanel() {
        setPreferredSize(new Dimension(width,height));
        inputMap.put(KeyStroke.getKeyStroke("pressed W"), "keyW");
        inputMap.put(KeyStroke.getKeyStroke("pressed A"), "keyA");
        inputMap.put(KeyStroke.getKeyStroke("pressed S"), "keyS");
        inputMap.put(KeyStroke.getKeyStroke("pressed D"), "keyD");
        inputMap.put(KeyStroke.getKeyStroke("pressed E"), "keyE");
        inputMap.put(KeyStroke.getKeyStroke("released W"), "keyRelW");
        inputMap.put(KeyStroke.getKeyStroke("released A"), "keyRelA");
        inputMap.put(KeyStroke.getKeyStroke("released S"), "keyRelS");
        inputMap.put(KeyStroke.getKeyStroke("released D"), "keyRelD");
        inputMap.put(KeyStroke.getKeyStroke("released E"), "keyRelE");
        inputMap.put(KeyStroke.getKeyStroke(27,0), "keyESC");
        inputMap.put(KeyStroke.getKeyStroke(9,0), "keyTAB");
        inputMap.put(KeyStroke.getKeyStroke(37,0), "keySWITCH");
        inputMap.put(KeyStroke.getKeyStroke(39,0), "keySWITCH");
        inputMap.put(KeyStroke.getKeyStroke(38,0), "keySWAP");
        inputMap.put(KeyStroke.getKeyStroke(40,0), "keySWAP");

        actionMap.put("keyW", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e){
                directionInput[0] = true;
            }
        });
        actionMap.put("keyA", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e){
                directionInput[2] = true;
            }
        });
        actionMap.put("keyS", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e){
                directionInput[1] = true;
            }
        });
        actionMap.put("keyD", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e){
                directionInput[3] = true;
            }
        });
        actionMap.put("keyRelW", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e){
                directionInput[0] = false;
            }
        });
        actionMap.put("keyRelA", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e){
                directionInput[2] = false;
            }
        });
        actionMap.put("keyRelS", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e){
                directionInput[1] = false;
            }
        });
        actionMap.put("keyRelD", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e){
                directionInput[3] = false;
            }
        });

        actionMap.put("keyE", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e){
                engineHandler();
            }
        });
        actionMap.put("keyESC", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e){
                exit();
            }
        });
        actionMap.put("keyTAB", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e){
                addVehicle();
            }
        });
        actionMap.put("keySWAP", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e){
                swapVehicle();
            }
        });
        actionMap.put("keySWITCH", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e){
                switchModel();
            }
        });

        Timer loop = new Timer(16, (ActionEvent e) -> {
            logicHandler();
            repaint();
        });
        loop.start();
        appInitLogic(); // Single execution upon application start
    }

    private void appInitLogic() {
        car = new Volvo240();
        position = new Position((double) width/2, (double) height/2);
        car.getPosition().setX(position.getX()*scalingFactor);
        car.getPosition().setY(position.getY()*scalingFactor);
    }

    public void logicHandler() {
        movementHandler();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawString("Model: " + car.getModelName(), 0, 50);
        g.drawString("Speed: " + car.getCurrentSpeed(), 0, 100);
        g.drawString("Gas: " + gasAmount, 0, 150);
        g.drawString("Brake: " + brakeAmount, 0, 200);
        drawCar(g);
        drawCars(g);
        Toolkit.getDefaultToolkit().sync();
    }

    public void drawCar(Graphics g) {
        g.setColor(car.getColor());
        g.fillPolygon(getAngledRect(car.getPosition()));
        g.setColor(Color.BLACK);
    }

    public void drawCars(Graphics g) {
        for (Car car : cars ) {
            g.setColor(car.getColor());
            g.fillPolygon(getAngledRect(car.getPosition()));
            g.setColor(Color.BLACK);
        }
    }

    public Polygon getAngledRect(Position position) {
        int width = 20;
        int length = 80;
        Position directionVector = car.getRotation().getDirectionVector();
        double x = position.getX()/scalingFactor;
        double y = position.getY()/scalingFactor;
        double dirVecX = directionVector.getX();
        double dirVecY = directionVector.getY();
        Position[] frontPositions = {new Position(x + (-1*dirVecY*width), y + (dirVecX*width)),new Position (x + (dirVecY*width), y + (-1*dirVecX*width))}; //Using the dot product rule as it avoids the Maths library and trigonometry and I forgot the trig rules for it
        Position[] rearPositions = {new Position(frontPositions[0].getX() + (-1*dirVecX*length), frontPositions[0].getY() + (-1*dirVecY*length)), new Position(frontPositions[1].getX() + (-1*dirVecX*length), frontPositions[1].getY() + (-1*dirVecY*length))}; // Reverse direction of the car's vector, and just paint the rear corners 'behind' the forward ones, multiplying dot circle coords with desired length
        // This math took me way too long to figure out, I really should've just looked up the formula...

        return new Polygon(new int[] {(int) frontPositions[0].getX(), (int) frontPositions[1].getX(), (int) rearPositions[1].getX(), (int) rearPositions[0].getX()},new int[] {(int) frontPositions[0].getY(), (int) frontPositions[1].getY(), (int) rearPositions[1].getY(), (int) rearPositions[0].getY()},4);
    }

    private void engineHandler() {
        if (car.getCurrentSpeed() > 0.0) {
            car.stopEngine();
        } else {
            car.startEngine();
        }
    }

    private void movementHandler() {
        if (car.getCurrentSpeed() > 0) {
            if (directionInput[0]) {
                gasAmount = Math.min(gasAmount + 3, 100);
            } else {
                gasAmount = Math.max(gasAmount - 3, 0);
            }
            if (directionInput[1]) {
                brakeAmount = Math.min(brakeAmount + 3, 100);
            } else {
                brakeAmount = Math.max(brakeAmount - 3, 0);
            }
            if (gasAmount < 10 && car.getCurrentSpeed() > 10) {
                car.brake(0.25);
            }
            if (directionInput[2]) {
                car.turnLeft();
                brakeAmount = Math.min(brakeAmount + 4, 100);

            }
            if (directionInput[3]) {
                car.turnRight();
                brakeAmount = Math.min(brakeAmount + 4, 100);
            }
            car.brake((double) brakeAmount /100);
            car.gas((double) gasAmount /100);

            car.move();
            position.setX(car.getPosition().getX()/scalingFactor);
            position.setY(car.getPosition().getY()/scalingFactor);
        } else if (gasAmount > 0 || brakeAmount > 0) {
            gasAmount = 0;
            brakeAmount = 0;
        }
    }

    private void addVehicle() {
        cars.addLast(car);
        car = new Volvo240();
        car.setPosition(findEligibleSpawn());
    }

    private void swapVehicle() {
        if (!cars.isEmpty()) {
            cars.addLast(car);
        }
        car = cars.getFirst();
    }

    private void switchModel() {
        Rotation previousRotation = car.getRotation();
        switch (car.getModelName()) {
            case "Volvo240" -> car = new Saab95();
            case "Saab95" -> car = new Scania();
            case "Scania" -> car = new VehicleTransport();
            case null, default -> car = new Volvo240();
        }
        car.getPosition().setX(position.getX()*scalingFactor);
        car.getPosition().setY(position.getY()*scalingFactor);
        car.getRotation().setRotation(previousRotation.getRotation());
    }

    private Position findEligibleSpawn() {
        Position newPosition = new Position(0,0);
        if (position.getX() - 100 > 100) {
            newPosition.setX((position.getX() - 100)*scalingFactor);
        } else {
            newPosition.setX((position.getX() + 100)*scalingFactor);
        }
        if (position.getY() - 100 > 100) {
            newPosition.setY((position.getY() - 100)*scalingFactor);
        } else {
            newPosition.setY((position.getY() + 100)*scalingFactor);
        }
        return newPosition;
    }

    public void exit() {
        System.exit(0);
    }

}
