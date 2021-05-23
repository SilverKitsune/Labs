package gui;

import figures.*;

import java.awt.event.ItemEvent;
import javax.swing.*;


public class MainWindow extends JFrame {
    private static final long serialVersionUID = -8972845754063080764L;
    JPanel panel;

    public MainWindow() {
        initUI();
    }

    private void initUI() {

        panel = new JPanel();

        //Changes the flow layout to nothing. Allows absolute positioning.
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        //Panel for the shape selection.
        JPanel shapePanel = new JPanel();
        shapePanel.setLayout(new BoxLayout(shapePanel, BoxLayout.X_AXIS));
        final JLabel shapeLabel = new JLabel("Select a Shape: ");
        final String[] shapeOptions = {"Parallelogram", "Quadrilateral", "Rectangle", "Rhombus", "Square", "Trapeze"};
        final JComboBox<String> shapeList = new JComboBox<>(shapeOptions);
        shapePanel.add(shapeLabel);
        shapePanel.add(shapeList);
        panel.add(shapePanel);

        final JPanel areaPanel = new JPanel();
        areaPanel.setLayout(new BoxLayout(areaPanel, BoxLayout.Y_AXIS));
        final JLabel areaLabel = new JLabel("Calculate Area");
        final JPanel areaParametersPanel = new JPanel();
        areaParametersPanel.setLayout(new BoxLayout(areaParametersPanel, BoxLayout.X_AXIS));
        areaPanel.add(areaLabel);
        areaPanel.add(areaParametersPanel);
        panel.add(areaPanel);

        JPanel perimeterPanel = new JPanel();
        JLabel perimeterLabel = new JLabel("Calculate Perimeter");
        perimeterPanel.setLayout(new BoxLayout(perimeterPanel, BoxLayout.Y_AXIS));
        final JPanel perimeterParametersPanel = new JPanel();
        perimeterParametersPanel.setLayout(new BoxLayout(perimeterParametersPanel, BoxLayout.X_AXIS));
        perimeterPanel.add(perimeterLabel);
        perimeterPanel.add(perimeterParametersPanel);
        panel.add(perimeterPanel);

        setupParallelogram(areaParametersPanel, perimeterParametersPanel);


        shapeList.addItemListener(event -> {
            if (event.getStateChange() == ItemEvent.SELECTED) {
                String shapeName = (String) shapeList.getSelectedItem();
                if (shapeName != null) {
                    areaParametersPanel.removeAll();
                    perimeterParametersPanel.removeAll();
                    switch (shapeName) {
                        case "Parallelogram":
                            setupParallelogram(areaParametersPanel, perimeterParametersPanel);
                            break;
                        case "Quadrilateral":
                            setupQuadrilateral(areaParametersPanel, perimeterParametersPanel);
                            break;
                        case "Rectangle":
                            setupRectangle(areaParametersPanel, perimeterParametersPanel);
                            break;
                        case "Rhombus":
                            setupRhombus(areaParametersPanel, perimeterParametersPanel);
                            break;
                        case "Square":
                            setupSquare(areaParametersPanel, perimeterParametersPanel);
                            break;
                        case "Trapeze":
                            setupTrapeze(areaParametersPanel, perimeterParametersPanel);
                            break;
                    }
                }
            }

        });
        add(panel);
        pack();
        setTitle("Shape Calculator");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void setupQuadrilateral(JPanel areaParametersPanel, JPanel perimeterParametersPanel) {
        String[] areaLabels = {"a = ", "b = ", "c = ", "d = ", "alfa = ", "beta = "};
        String[] perimeterLabels = {"a = ", "b = ", "c = ", "d = "};
        JLabel areaImageLabel = new JLabel(new ImageIcon("quadrilateral_area.PNG"));
        JLabel perimeterImageLabel = new JLabel(new ImageIcon("quadrilateral_perimeter.PNG"));
        areaParametersPanel.add(areaImageLabel);
        areaParametersPanel.add(fields(areaLabels,"Quadrilateral", "area"));

        perimeterParametersPanel.add(perimeterImageLabel);
        perimeterParametersPanel.add(fields(perimeterLabels,"Quadrilateral", "perimeter"));

        areaParametersPanel.revalidate();
        areaParametersPanel.repaint();
        perimeterParametersPanel.revalidate();
        perimeterParametersPanel.repaint();
        pack();
    }

    private void setupParallelogram(JPanel areaParametersPanel, JPanel perimeterParametersPanel) {
        String[] areaLabels = {"a = ", "h = "};
        String[] perimeterLabels = {"a = ", "b = "};
        JLabel areaImageLabel = new JLabel(new ImageIcon("parallelogram_area.PNG"));
        JLabel perimeterImageLabel = new JLabel(new ImageIcon("parallelogram_perimeter.PNG"));
        areaParametersPanel.add(areaImageLabel);
        areaParametersPanel.add(fields(areaLabels,"Parallelogram", "area"));

        perimeterParametersPanel.add(perimeterImageLabel);
        perimeterParametersPanel.add(fields(perimeterLabels,"Parallelogram", "perimeter"));

        areaParametersPanel.revalidate();
        areaParametersPanel.repaint();
        perimeterParametersPanel.revalidate();
        perimeterParametersPanel.repaint();
        pack();
    }

    private void setupTrapeze(JPanel areaParametersPanel, JPanel perimeterParametersPanel) {
        String[] areaLabels = {"a = ", "b = ", "h = "};
        String[] perimeterLabels = {"a = ", "b = ", "c = ", "d = "};
        JLabel areaImageLabel = new JLabel(new ImageIcon("trapeze_area.PNG"));
        JLabel perimeterImageLabel = new JLabel(new ImageIcon("trapeze_perimeter.PNG"));
        areaParametersPanel.add(areaImageLabel);
        areaParametersPanel.add(fields(areaLabels,"Trapeze", "area"));

        perimeterParametersPanel.add(perimeterImageLabel);
        perimeterParametersPanel.add(fields(perimeterLabels,"Trapeze", "perimeter"));

        areaParametersPanel.revalidate();
        areaParametersPanel.repaint();
        perimeterParametersPanel.revalidate();
        perimeterParametersPanel.repaint();
        pack();
    }

    private void setupSquare(JPanel areaParametersPanel, JPanel perimeterParametersPanel) {
        String[] labels = {"a = "};
        JLabel areaImageLabel = new JLabel(new ImageIcon("square.PNG"));
        JLabel perimeterImageLabel = new JLabel(new ImageIcon("square.PNG"));
        areaParametersPanel.add(areaImageLabel);
        areaParametersPanel.add(fields(labels,"Square", "area"));

        perimeterParametersPanel.add(perimeterImageLabel);
        perimeterParametersPanel.add(fields(labels,"Square", "perimeter"));

        areaParametersPanel.revalidate();
        areaParametersPanel.repaint();
        perimeterParametersPanel.revalidate();
        perimeterParametersPanel.repaint();
        pack();
    }

    private void setupRhombus(JPanel areaParametersPanel, JPanel perimeterParametersPanel) {
        String[] areaLabels = {"a = ", "h = "};
        String[] perimeterLabels = {"a = "};
        JLabel areaImageLabel = new JLabel(new ImageIcon("rhombus_area.PNG"));
        JLabel perimeterImageLabel = new JLabel(new ImageIcon("rhombus_perimeter.PNG"));
        areaParametersPanel.add(areaImageLabel);
        areaParametersPanel.add(fields(areaLabels,"Rhombus", "area"));

        perimeterParametersPanel.add(perimeterImageLabel);
        perimeterParametersPanel.add(fields(perimeterLabels,"Rhombus", "perimeter"));

        areaParametersPanel.revalidate();
        areaParametersPanel.repaint();
        perimeterParametersPanel.revalidate();
        perimeterParametersPanel.repaint();
        pack();
    }

    private void setupRectangle(JPanel areaParametersPanel, JPanel perimeterParametersPanel) {
        String[] labels = {"a = ", "b = "};
        JLabel areaImageLabel = new JLabel(new ImageIcon("rectangle.PNG"));
        JLabel perimeterImageLabel = new JLabel(new ImageIcon("rectangle.PNG"));
        areaParametersPanel.add(areaImageLabel);
        areaParametersPanel.add(fields(labels,"Rectangle", "area"));

        perimeterParametersPanel.add(perimeterImageLabel);
        perimeterParametersPanel.add(fields(labels,"Rectangle", "perimeter"));

        areaParametersPanel.revalidate();
        areaParametersPanel.repaint();
        perimeterParametersPanel.revalidate();
        perimeterParametersPanel.repaint();
        pack();
    }


    private JPanel fields(String[] labels, String figure, String operation) {
        JPanel finalPanel = new JPanel();
        JTextField[] textFields = new JTextField[labels.length];
        finalPanel.setLayout(new BoxLayout(finalPanel, BoxLayout.Y_AXIS));
        for (int i = 0; i < labels.length; i++) {
            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
            panel.add(new JLabel(labels[i]));
            textFields[i] = new JTextField(24);
            panel.add(textFields[i]);
            finalPanel.add(panel);
        }
        JPanel solve = new JPanel();
        solve.setLayout(new BoxLayout(solve, BoxLayout.X_AXIS));
        JButton solveButton = new JButton("Solve");
        final JTextField solutionField = new JTextField(24);
        setSolveButtonListener(solveButton, figure, operation, textFields, solutionField);
        solve.add(solveButton);
        solve.add(solutionField);
        finalPanel.add(solve);
        return finalPanel;
    }

    private void setSolveButtonListener(JButton solveButton, String figure, String operation, JTextField[] textFields, JTextField solutionField) {
        solveButton.addActionListener(event -> {
            switch (figure) {
                case "Parallelogram":
                    Parallelogram parallelogram;
                    if (operation.equals("area")) {
                        parallelogram = new Parallelogram(
                                Double.parseDouble(textFields[0].getText()),
                                0,
                                Double.parseDouble(textFields[1].getText()));
                        solutionField.setText(parallelogram.area() + "");
                    } else {
                        parallelogram = new Parallelogram(
                                Double.parseDouble(textFields[0].getText()),
                                Double.parseDouble(textFields[1].getText()), 0);
                        solutionField.setText(parallelogram.perimeter() + "");
                    }
                    break;
                case "Quadrilateral":
                    Quadrilateral quadrilateral;
                    if (operation.equals("area")) {
                        quadrilateral = new Quadrilateral(
                                Double.parseDouble(textFields[0].getText()),
                                Double.parseDouble(textFields[1].getText()),
                                Double.parseDouble(textFields[2].getText()),
                                Double.parseDouble(textFields[3].getText()),
                                Double.parseDouble(textFields[4].getText()),
                                Double.parseDouble(textFields[5].getText()));
                        solutionField.setText(quadrilateral.area() + "");
                    } else {
                        quadrilateral = new Quadrilateral(
                                Double.parseDouble(textFields[0].getText()),
                                Double.parseDouble(textFields[1].getText()),
                                Double.parseDouble(textFields[2].getText()),
                                Double.parseDouble(textFields[3].getText()));
                        solutionField.setText(quadrilateral.perimeter() + "");
                    }
                    break;
                case "Rectangle":
                    Rectangle rectangle = new Rectangle(
                            Double.parseDouble(textFields[0].getText()),
                            Double.parseDouble(textFields[1].getText()));
                    if (operation.equals("area"))
                        solutionField.setText(rectangle.area() + "");
                    else
                        solutionField.setText(rectangle.perimeter() + "");
                    break;
                case "Rhombus":
                    Rhombus rhombus;
                    if (operation.equals("area")) {
                        rhombus = new Rhombus(
                                Double.parseDouble(textFields[0].getText()),
                                Double.parseDouble(textFields[1].getText()));
                        solutionField.setText(rhombus.area() + "");
                    } else {
                        rhombus = new Rhombus(
                                Double.parseDouble(textFields[0].getText()), 0);
                        solutionField.setText(rhombus.perimeter() + "");
                    }
                    break;
                case "Square":
                    Square square = new Square(Double.parseDouble(textFields[0].getText()));
                    if (operation.equals("area"))
                        solutionField.setText(square.area() + "");
                    else
                        solutionField.setText(square.perimeter() + "");
                    break;
                case "Trapeze":
                    Trapeze trapeze;
                    if (operation.equals("area")) {
                        trapeze = new Trapeze(
                                Double.parseDouble(textFields[0].getText()),
                                Double.parseDouble(textFields[1].getText()),
                                Double.parseDouble(textFields[2].getText()));
                        solutionField.setText(trapeze.area() + "");
                    } else {
                        trapeze = new Trapeze(
                                Double.parseDouble(textFields[0].getText()),
                                Double.parseDouble(textFields[1].getText()),
                                Double.parseDouble(textFields[2].getText()),
                                Double.parseDouble(textFields[3].getText()),
                                0);
                        solutionField.setText(trapeze.perimeter() + "");
                    }
                    break;
            }
        });
    }



}
