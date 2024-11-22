# Ανάλυση Εφαρμογής

## Κληρονομικότητα και Ιεραρχία Κλάσεων

Το πρόγραμμα είναι σχεδιασμένο με ξεκάθαρη δομή κληρονομικότητας:

### Βασική Κλάση: `Vehicle`
Αναπαριστά ένα γενικό όχημα με χαρακτηριστικά:

- **`licensePlate` (String):** Αριθμός κυκλοφορίας.
- **`model` (String):** Μοντέλο οχήματος.
- **`year` (int):** Έτος κατασκευής.

Παρέχει μεθόδους:

- **`displayInfo()`**: Εμφανίζει τις λεπτομέρειες του οχήματος.
- **`getType()`**: Επιστρέφει τον τύπο του οχήματος (υπερκαλύπτεται στις υποκλάσεις).

---

### Υποκλάσεις: `Car` και `Motorcycle`

#### `Car`
- **Κληρονομεί** από την `Vehicle`.
- **Προσθέτει** το χαρακτηριστικό:
  - **`doors` (int):** Αριθμός θυρών.
- **Υπερκαλύπτει**:
  - **`displayInfo()`** για να συμπεριλάβει τον αριθμό θυρών.
  - **`getType()`** για να επιστρέφει `"Car"`.

#### `Motorcycle`
- **Κληρονομεί** από την `Vehicle`.
- **Προσθέτει** το χαρακτηριστικό:
  - **`hasSidecar` (boolean):** Υποδεικνύει αν υπάρχει καλάθι.
- **Υπερκαλύπτει**:
  - **`displayInfo()`** για να συμπεριλάβει πληροφορίες για το καλάθι.
  - **`getType()`** για να επιστρέφει `"Motorcycle"`.

---

## Διαχείριση Οχημάτων στην `Main`

Η κλάση `Main` λειτουργεί ως το σημείο εκκίνησης της εφαρμογής και είναι υπεύθυνη για τη διαχείριση πληροφοριών οχημάτων και την αλληλεπίδραση με τον χρήστη.

Παρουσιάζει την **κληρονομικότητα** και τον **πολυμορφισμό** μέσω των παρακάτω λειτουργιών:

### Δημιουργία Αντικειμένων `Car` και `Motorcycle`

Η μέθοδος **`preloadVehicles()`** δημιουργεί στιγμιότυπα των `Car` και `Motorcycle` με διάφορα χαρακτηριστικά:

```java
 vehicles.add(new Car("ABC123", "Toyota Corolla", 2020, 4));
 vehicles.add(new Motorcycle("XYZ789", "Harley Davidson", 2019, false));
 vehicles.add(new Car("DEF456", "Honda Civic", 2021, 2));
```

### Αυτά τα αντικείμενα προστίθενται στη λίστα vehicles, επιδεικνύοντας τη χρήση πολυμορφισμού, καθώς και οι δύο υποκλάσεις αποθηκεύονται σε συλλογή της κλάσης Vehicle.

---


## vΕμφάνιση Πληροφοριών Οχημάτων

Το πρόγραμμα καλεί την υπερκαλυμμένη μέθοδο **displayInfo()** για κάθε αντικείμενο **Vehicle**, διασφαλίζοντας ότι εμφανίζονται οι σωστές λεπτομέρειες ειδικά για κάθε υποκατηγορία:

```java
for (Vehicle vehicle : vehicles) {
    addVehicleCard(vehicle); // Εμφανίζει λεπτομέρειες χρησιμοποιώντας το displayInfo()
}
```

    Κάθε υποκλάση παρέχει τις δικές της ειδικές λεπτομέρειες:
        Αριθμός θυρών για Car.
        Καλάθι για Motorcycle.

## Αλληλεπίδραση με τον Χρήστη μέσω Μενού

    Προβολή Όλων των Οχημάτων:
        Εμφανίζει όλα τα οχήματα χρησιμοποιώντας τη μέθοδο displayInfo().

    Προσθήκη Οχήματος:
        Επιτρέπει στους χρήστες να δημιουργήσουν νέα στιγμιότυπα Car ή Motorcycle δυναμικά.
        Τα νέα αντικείμενα αποθηκεύονται στη λίστα vehicles.

    Αναζήτηση με Βάση τον Αριθμό Κυκλοφορίας:
        Αναζητά ένα όχημα βάσει του licensePlate και εμφανίζει τις πληροφορίες του χρησιμοποιώντας τη μέθοδο displayInfo().

    Φιλτράρισμα Οχημάτων με Βάση τον Τύπο:
        Χρησιμοποιεί τη μέθοδο getType() για να φιλτράρει αντικείμενα Car και Motorcycle, επιδεικνύοντας τον πολυμορφισμό.

## Ικανοποίηση Απαιτήσεων

    Δημιουργία Αντικειμένων Car και Motorcycle:
        Στιγμιότυπα και των δύο κλάσεων δημιουργούνται:
            Στη μέθοδο preloadVehicles().
            Δυναμικά μέσω του GUI με τη λειτουργία "Προσθήκη Οχήματος".

    Κλήση Μεθόδων για Εμφάνιση Πληροφοριών Οχημάτων:
        Η μέθοδος displayInfo() καλείται για κάθε αντικείμενο:

        System.out.println(vehicle.displayInfo());

### Διασφαλίζεται ότι οι πληροφορίες εμφανίζονται σωστά, επιβεβαιώνοντας τη λειτουργικότητα της κληρονομικότητας και των υπερκαλυμμένων μεθόδων.

## Συμπέρασμα

Το πρόγραμμα ικανοποιεί τις απαιτήσεις της εργασίας μέσω:

    Δομής Κληρονομικότητας:
        Καθαρή ιεραρχία κλάσεων (Vehicle, Car, Motorcycle).
    Πολυμορφισμού:
        Υπερκαλυμμένες μέθοδοι (displayInfo(), getType()).
    Ευέλικτης Διαχείρισης Οχημάτων:
        Προσθήκη, προβολή, φιλτράρισμα, και αναζήτηση.

- Της χρήσης κληρονομικότητας (Vehicle, Car, και Motorcycle κλάσεις).
- Της υλοποίησης μεθόδων που επαναπροσδιορίζονται (displayInfo() και getType()).
- Της παροχής ενός πλήρους γραφικού περιβάλλοντος για τη διαχείριση και την αλληλεπίδραση με πληροφορίες οχημάτων δυναμικά.
- Της σωστής εμφάνισης δεδομένων για κάθε τύπο οχήματος, εξασφαλίζοντας την αποτελεσματική χρήση πολυμορφισμού
