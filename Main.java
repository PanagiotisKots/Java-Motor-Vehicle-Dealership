import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

// Base Vehicle class
class Vehicle {
    private String licensePlate;
    private String model;
    private int year;

    public Vehicle(String licensePlate, String model, int year) {
        this.licensePlate = licensePlate;
        this.model = model;
        this.year = year;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public String getModel() {
        return model;
    }

    public int getYear() {
        return year;
    }

    public String displayInfo() {
        return "License Plate: " + licensePlate + ", Model: " + model + ", Year: " + year;
    }

    public String getType() {
        return "Vehicle";
    }
}

// Subclass for Car
class Car extends Vehicle {
    private int doors;

    public Car(String licensePlate, String model, int year, int doors) {
        super(licensePlate, model, year);
        this.doors = doors;
    }

    @Override
    public String displayInfo() {
        return super.displayInfo() + ", Doors: " + doors;
    }

    @Override
    public String getType() {
        return "Car";
    }
}

// Subclass for Motorcycle
class Motorcycle extends Vehicle {
    private boolean hasSidecar;

    public Motorcycle(String licensePlate, String model, int year, boolean hasSidecar) {
        super(licensePlate, model, year);
        this.hasSidecar = hasSidecar;
    }

    @Override
    public String displayInfo() {
        return super.displayInfo() + ", Has Sidecar: " + (hasSidecar ? "Yes" : "No");
    }

    @Override
    public String getType() {
        return "Motorcycle";
    }
}

public class Main {
    private JFrame frame;
    private JPanel vehicleCardsPanel;
    private ArrayList<Vehicle> vehicles;

    public Main() {
        vehicles = new ArrayList<>();
        preloadVehicles(); // Add initial vehicles
        setupMainGUI();
    }

    private void preloadVehicles() {
        vehicles.add(new Car("ABC123", "Toyota Corolla", 2020, 4));
        vehicles.add(new Motorcycle("XYZ789", "Harley Davidson", 2019, false));
        vehicles.add(new Car("DEF456", "Honda Civic", 2021, 2));
    }

    private void setupMainGUI() {
        frame = new JFrame("Car Dealership - Main");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout());

        // Top Menu Bar
        JMenuBar menuBar = new JMenuBar();

        JMenu actionsMenu = new JMenu("Actions");
        JMenuItem addVehicleItem = new JMenuItem("Add Vehicle");
        JMenuItem searchVehicleItem = new JMenuItem("Search Vehicle");
        JMenuItem filterVehicleItem = new JMenuItem("Filter Vehicles");
        JMenuItem viewAllVehiclesItem = new JMenuItem("View All Vehicles");
        JMenuItem exitItem = new JMenuItem("Exit");

        actionsMenu.add(addVehicleItem);
        actionsMenu.add(searchVehicleItem);
        actionsMenu.add(filterVehicleItem);
        actionsMenu.add(viewAllVehiclesItem);
        actionsMenu.add(exitItem);

        menuBar.add(actionsMenu);
        frame.setJMenuBar(menuBar);

        // Center Panel: Vehicle Cards
        vehicleCardsPanel = new JPanel();
        vehicleCardsPanel.setLayout(new BoxLayout(vehicleCardsPanel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(vehicleCardsPanel);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Saved Vehicles"));

        frame.add(scrollPane, BorderLayout.CENTER);

        // Action Listeners for Menu Items
        addVehicleItem.addActionListener(e -> openAddVehicleGUI());
        searchVehicleItem.addActionListener(e -> openSearchVehicleGUI());
        filterVehicleItem.addActionListener(e -> openFilterVehicleGUI());
        viewAllVehiclesItem.addActionListener(e -> displayAllVehicles());
        exitItem.addActionListener(e -> System.exit(0));

        // Display Preloaded Vehicles
        displayAllVehicles();

        frame.setVisible(true);
    }

    private void openAddVehicleGUI() {
        JFrame addFrame = new JFrame("Add Vehicle");
        addFrame.setSize(400, 300);
        addFrame.setLayout(new GridLayout(6, 2));

        JTextField licensePlateField = new JTextField();
        JTextField modelField = new JTextField();
        JTextField yearField = new JTextField();
        JTextField doorsField = new JTextField();
        JTextField sidecarField = new JTextField();

        addFrame.add(new JLabel("License Plate:"));
        addFrame.add(licensePlateField);
        addFrame.add(new JLabel("Model:"));
        addFrame.add(modelField);
        addFrame.add(new JLabel("Year:"));
        addFrame.add(yearField);
        addFrame.add(new JLabel("Doors (Car Only):"));
        addFrame.add(doorsField);
        addFrame.add(new JLabel("Has Sidecar (Motorcycle Only, true/false):"));
        addFrame.add(sidecarField);

        JButton addCarButton = new JButton("Add Car");
        JButton addMotorcycleButton = new JButton("Add Motorcycle");
        addFrame.add(addCarButton);
        addFrame.add(addMotorcycleButton);

        addCarButton.addActionListener(e -> {
            try {
                String licensePlate = licensePlateField.getText();
                String model = modelField.getText();
                int year = Integer.parseInt(yearField.getText());
                int doors = Integer.parseInt(doorsField.getText());

                vehicles.add(new Car(licensePlate, model, year, doors));
                displayAllVehicles();
                addFrame.dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(addFrame, "Invalid input. Please check the values.");
            }
        });

        addMotorcycleButton.addActionListener(e -> {
            try {
                String licensePlate = licensePlateField.getText();
                String model = modelField.getText();
                int year = Integer.parseInt(yearField.getText());
                boolean hasSidecar = Boolean.parseBoolean(sidecarField.getText());

                vehicles.add(new Motorcycle(licensePlate, model, year, hasSidecar));
                displayAllVehicles();
                addFrame.dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(addFrame, "Invalid input. Please check the values.");
            }
        });

        addFrame.setVisible(true);
    }
    private void openSearchVehicleGUI() {
        JFrame searchFrame = new JFrame("Search Vehicle");
        searchFrame.setSize(440, 200);
        searchFrame.setLocationRelativeTo(null); // Center the frame
        searchFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Use GridBagLayout for better alignment
        searchFrame.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Add padding around components
        gbc.anchor = GridBagConstraints.WEST;

        // Components
        JLabel instructionLabel = new JLabel("Enter License Plate:");
        JTextField searchField = new JTextField(20); // Default width
        searchField.setPreferredSize(new Dimension(250, 25)); // Set preferred size for input field
        JButton searchButton = new JButton("Search");
        JLabel resultLabel = new JLabel("Result: ");

        // Style components
        instructionLabel.setFont(new Font("Arial", Font.BOLD, 14));
        searchButton.setFont(new Font("Arial", Font.PLAIN, 12));
        searchButton.setBackground(new Color(60, 120, 180)); // Stylish blue button
        searchButton.setForeground(Color.WHITE);
        searchButton.setFocusPainted(false);
        resultLabel.setFont(new Font("Arial", Font.PLAIN, 12));

        // Layout constraints for components
        // Add instruction label in row 0
        gbc.gridx = 0;
        gbc.gridy = 0;
        searchFrame.add(instructionLabel, gbc);

        // Add search field in row 0, column 1 (same row as the label)
        gbc.gridx = 1;
        searchFrame.add(searchField, gbc);

        // Add search button in row 1, column 0 (below the label)
        gbc.gridx = 0;
        gbc.gridy = 1;
        searchFrame.add(searchButton, gbc);

        // Add result label in row 2, column 0 (below the search button)
        gbc.gridx = 0;
        gbc.gridy = 2;
        searchFrame.add(resultLabel, gbc);

        // Search button action listener
        searchButton.addActionListener(e -> {
            String licensePlate = searchField.getText().trim();
            for (Vehicle vehicle : vehicles) {
                if (vehicle.getLicensePlate().equalsIgnoreCase(licensePlate)) {
                    resultLabel.setText("Result: " + vehicle.displayInfo());
                    return;
                }
            }
            resultLabel.setText("Result: Not Found");
        });

        searchFrame.setVisible(true);
    }


    private void openFilterVehicleGUI() {
        JFrame filterFrame = new JFrame("Filter Vehicles");
        filterFrame.setSize(400, 200);
        filterFrame.setLocationRelativeTo(null); // Center the frame
        filterFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Use GridBagLayout for better alignment
        filterFrame.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Add some padding around components
        gbc.anchor = GridBagConstraints.WEST;

        // Components
        JLabel filterLabel = new JLabel("Select Type:");
        JComboBox<String> filterComboBox = new JComboBox<>(new String[]{"All", "Car", "Motorcycle"});
        JButton filterButton = new JButton("Filter");

        // Style components
        filterLabel.setFont(new Font("Arial", Font.BOLD, 14));
        filterButton.setFont(new Font("Arial", Font.PLAIN, 12));
        filterButton.setBackground(new Color(60, 120, 180)); // Stylish blue button
        filterButton.setForeground(Color.WHITE);
        filterButton.setFocusPainted(false);

        // Layout constraints for components
        gbc.gridx = 0;
        gbc.gridy = 0;
        filterFrame.add(filterLabel, gbc);

        gbc.gridx = 1;
        filterFrame.add(filterComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        filterFrame.add(filterButton, gbc);

        // Filter button action listener
        filterButton.addActionListener(e -> {
            String filterType = (String) filterComboBox.getSelectedItem();
            vehicleCardsPanel.removeAll();
            for (Vehicle vehicle : vehicles) {
                if (filterType.equals("All") || vehicle.getType().equals(filterType)) {
                    addVehicleCard(vehicle); // Assuming this method handles adding a vehicle card to the panel
                }
            }
            vehicleCardsPanel.revalidate();
            vehicleCardsPanel.repaint();
            filterFrame.dispose();
        });

        filterFrame.setVisible(true);
    }

    private void displayAllVehicles() {
        vehicleCardsPanel.removeAll();
        for (Vehicle vehicle : vehicles) {
            addVehicleCard(vehicle);
        }
        vehicleCardsPanel.revalidate();
        vehicleCardsPanel.repaint();
    }

    private void addVehicleCard(Vehicle vehicle) {
        // Create a card panel with a BorderLayout for better control of positioning
        JPanel card = new JPanel(new GridBagLayout());
        card.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
        card.setBackground(new Color(245, 245, 245)); // Light grey background for the card
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Padding between components
        gbc.anchor = GridBagConstraints.WEST;

        // Title label for the vehicle (display license plate and model)
        JLabel titleLabel = new JLabel(vehicle.getModel() + " (" + vehicle.getLicensePlate() + ")");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setForeground(new Color(60, 60, 60)); // Dark text color
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        card.add(titleLabel, gbc);

        // Vehicle year label
        JLabel yearLabel = new JLabel("Year: " + vehicle.getYear());
        yearLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        yearLabel.setForeground(new Color(80, 80, 80));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        card.add(yearLabel, gbc);

        // Vehicle type label (Car, Motorcycle, etc.)
        JLabel typeLabel = new JLabel("Type: " + vehicle.getType());
        typeLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        typeLabel.setForeground(new Color(80, 80, 80));
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        card.add(typeLabel, gbc);

        // Vehicle specific details (Car doors or Motorcycle sidecar)
        JLabel detailsLabel = new JLabel(vehicle.displayInfo());
        detailsLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        detailsLabel.setForeground(new Color(100, 100, 100));
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        card.add(detailsLabel, gbc);

        // Add the card to the panel
        vehicleCardsPanel.add(card);
    }


    public static void main(String[] args) {
        new Main();
    }
}
