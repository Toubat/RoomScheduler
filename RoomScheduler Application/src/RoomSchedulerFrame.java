import java.util.ArrayList;

public class RoomSchedulerFrame extends javax.swing.JFrame
{

    
    /**
     * Creates new form RoomSchedulerFrame
     */        

    public RoomSchedulerFrame()
    {
        super("Room Scheduler");
        initComponents();
        // Load the combo boxes with data.
        rebuildFacultyComboBoxes();
        rebuildDateComboBoxes();
        rebuildDropRoomComboBox();
    }
    
    public void rebuildFacultyComboBoxes()
    {
        // rebuild faculty combo boxes
        ArrayList<String> faculty = Faculty.getFacultyList();
        facultyResComboBox.setModel(new javax.swing.DefaultComboBoxModel(faculty.toArray()));       
        reserveFacultyComboBox.setModel(new javax.swing.DefaultComboBoxModel(faculty.toArray()));  
        facultyStatusComboBox.setModel(new javax.swing.DefaultComboBoxModel(faculty.toArray()));  
    }
    
    public void rebuildDateComboBoxes() {
        // rebuild date combo boxes
        ArrayList<String> dates = Dates.getAllDates();
        reserveDateComboBox.setModel(new javax.swing.DefaultComboBoxModel(dates.toArray()));
        dateReserveComboBox.setModel(new javax.swing.DefaultComboBoxModel(dates.toArray()));
        dateResComboBox.setModel(new javax.swing.DefaultComboBoxModel(dates.toArray()));
    }
    
    public void rebuildReservationByDateList(String dateString) {
        // rebuild reservation by date list
        ArrayList<ReservationEntry> reservations = ReservationQueries.getReservationsByDate(dateString);
        reservationByDateList.setModel(new javax.swing.DefaultComboBoxModel(reservations.toArray()));
    }
    
    public void rebuildWaitlistList() {
        // rebuild waitlist list
        ArrayList<WaitlistEntry> waitlist = WaitlistQueries.getWaitlist();
        waitlistList.setModel(new javax.swing.DefaultComboBoxModel(waitlist.toArray()));
    }
    
    public void rebuildFacultyStatusList() {
        // rebuild faculty status list
        String faculty = facultyStatusComboBox.getItemAt(facultyStatusComboBox.getSelectedIndex()).toString();
        ArrayList<ReservationEntry> reservationEntry = ReservationQueries.getReservationsByFaculty(faculty);
        ArrayList<WaitlistEntry> waitlistEntry = WaitlistQueries.getWaitlistByFaculty(faculty);
        reservedRoomList.setModel(new javax.swing.DefaultComboBoxModel(reservationEntry.toArray()));
        waitlistRoomList.setModel(new javax.swing.DefaultComboBoxModel(waitlistEntry.toArray()));
    }
 
    public void rebuildAddRoomList(ArrayList<String> infoList) {
        // rebuild add room list
        addRoomList.setModel(new javax.swing.DefaultComboBoxModel(infoList.toArray()));
    }
    
    public void rebuildDropRoomComboBox() {
        // rebuild drop room combo box
        ArrayList<String> rooms = RoomQueries.getRooms();
        dropRoomComboBox.setModel(new javax.swing.DefaultComboBoxModel(rooms.toArray()));
    }
    
    public void rebuildDropRoomList(ArrayList<String> infoList) {
        // rebuild drop room list
        dropRoomList.setModel(new javax.swing.DefaultComboBoxModel(infoList.toArray()));
    }
    
    public void assignRoomToFaculty(String name, String date, int seats) {
       // reserve room for faculty if there is room available; otherwise move faculty into waitlist
       ArrayList<ReservationEntry> reservations = ReservationQueries.getReservationsByFaculty(name);
       for (ReservationEntry entry : reservations) {
           if (entry.getDate().equals(date)) {
               reserveStatusLabel.setText("Faculty can only reserve one room in a specific date");
               return;
           }
       }
       ArrayList<RoomEntry> possibleRooms = RoomQueries.getRoomsBySeats(seats);
       ArrayList<String> roomsReserved = ReservationQueries.getRoomsReservedByDate(date);
       for (RoomEntry roomEntry : possibleRooms) {
           String room = roomEntry.getName();
           if (! roomsReserved.contains(room)) {
               ReservationEntry entry = new ReservationEntry(name, room, date, seats);
               ReservationQueries.addReservationEntry(entry);
               reserveStatusLabel.setText(name + " has reserved room " + room + " with " + roomEntry.getSeats() + " seats");
               return;
           }
       }
       
       WaitlistQueries.addWaitlistEntry(name, date, seats);
       System.out.println(possibleRooms);
       reserveStatusLabel.setText(name + " has been added into waitlist");
       
   }
    
    public void assignRoomToFaculty(ArrayList<ReservationEntry> reservations, ArrayList<String> infoList) {
        // reserve room for faculty in reservations if there is room available; otherwise move faculty into waitlist
        // append information into infoList
        for(ReservationEntry entry : reservations) {
            boolean reservedAgain = false;
            ArrayList<RoomEntry> possibleRooms = RoomQueries.getRoomsBySeats(entry.getSeats());
            ArrayList<String> roomsReserved = ReservationQueries.getRoomsReservedByDate(entry.getDate());
            for (RoomEntry roomEntry : possibleRooms) {
                String room = roomEntry.getName();
                if (! roomsReserved.contains(room)) {
                    ReservationQueries.addReservationEntry(new ReservationEntry(entry.getFaculty(), room, entry.getDate(), entry.getSeats()));
                    infoList.add(entry.getFaculty() + " has reserved room " + room + " with " + roomEntry.getSeats() + " seats on " + entry.getDate());
                    reservedAgain = true;
                    break;
                }
            }
            if (! reservedAgain) {
                WaitlistQueries.addWaitlistEntry(entry.getFaculty(), entry.getDate(), entry.getSeats());
                infoList.add(entry.getFaculty() + " has been moved into waitlist on " + entry.getDate());
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jLabel1 = new javax.swing.JLabel();
        facultyStatusPanel = new javax.swing.JTabbedPane();
        addFacultyPanel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        addFacultyTextField = new javax.swing.JTextField();
        addFacultyButton = new javax.swing.JButton();
        addFacultyStatusLabel = new javax.swing.JLabel();
        reserveRoomPanel = new javax.swing.JPanel();
        FacultyReserveLabel = new javax.swing.JLabel();
        reserveFacultyComboBox = new javax.swing.JComboBox<String>();
        DateReserveLabel = new javax.swing.JLabel();
        reserveDateComboBox = new javax.swing.JComboBox<String>();
        SeatsReserveLabel = new javax.swing.JLabel();
        reserveSeatsTextField = new javax.swing.JTextField();
        reserveSubmitButton = new javax.swing.JButton();
        reserveStatusLabel = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        reservationByDateScrollPanel = new javax.swing.JScrollPane();
        reservationByDateList = new javax.swing.JList();
        reservationRecordLabel = new javax.swing.JLabel();
        dateReserveLabel = new javax.swing.JLabel();
        searchReserveButton = new javax.swing.JButton();
        dateReserveComboBox = new javax.swing.JComboBox();
        AddDropRoomPanel = new javax.swing.JPanel();
        addRoomLabel = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        roomNameTextField = new javax.swing.JTextField();
        seatsTextField = new javax.swing.JTextField();
        dropRoomLabel = new javax.swing.JLabel();
        dropRoomComboBox = new javax.swing.JComboBox();
        dropRoomButton = new javax.swing.JButton();
        addDropRoomLabel = new javax.swing.JLabel();
        addRoomButton = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        dropRoomList = new javax.swing.JList();
        jScrollPane6 = new javax.swing.JScrollPane();
        addRoomList = new javax.swing.JList();
        waitlistPanel = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        waitlistList = new javax.swing.JList();
        waitlistLabel = new javax.swing.JLabel();
        refreshWaitlistButton = new javax.swing.JButton();
        addDatePanel = new javax.swing.JPanel();
        yearLabel = new javax.swing.JLabel();
        monthLabel = new javax.swing.JLabel();
        dayLabel = new javax.swing.JLabel();
        addDateSubmitButton = new javax.swing.JButton();
        addDateStatusLabel = new javax.swing.JLabel();
        yearComboBox = new javax.swing.JComboBox();
        dayComboBox = new javax.swing.JComboBox();
        monthComboBox = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        cancelResFacultyLabel = new javax.swing.JLabel();
        cancelResDateLabel = new javax.swing.JLabel();
        facultyResComboBox = new javax.swing.JComboBox();
        dateResComboBox = new javax.swing.JComboBox();
        cancelResSubmitButton = new javax.swing.JButton();
        cancelResStatusLabel = new javax.swing.JLabel();
        resStatusLabel = new javax.swing.JLabel();
        waitlistStatusLabel = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        facultyStatusLabel = new javax.swing.JLabel();
        facultyStatusComboBox = new javax.swing.JComboBox();
        facultyStatusSubmitButton = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        reservedRoomList = new javax.swing.JList();
        jScrollPane4 = new javax.swing.JScrollPane();
        waitlistRoomList = new javax.swing.JList();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Consolas", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Room Scheduler");

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel2.setText("Faculty Name: ");

        addFacultyTextField.setColumns(20);

        addFacultyButton.setText("Submit");
        addFacultyButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addFacultyButtonActionPerformed(evt);
            }
        });

        addFacultyStatusLabel.setText("    ");

        javax.swing.GroupLayout addFacultyPanelLayout = new javax.swing.GroupLayout(addFacultyPanel);
        addFacultyPanel.setLayout(addFacultyPanelLayout);
        addFacultyPanelLayout.setHorizontalGroup(
            addFacultyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addFacultyPanelLayout.createSequentialGroup()
                .addGroup(addFacultyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(addFacultyPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(addFacultyStatusLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 401, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(addFacultyPanelLayout.createSequentialGroup()
                        .addGap(137, 137, 137)
                        .addGroup(addFacultyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(addFacultyPanelLayout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(addFacultyTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(addFacultyPanelLayout.createSequentialGroup()
                                .addGap(133, 133, 133)
                                .addComponent(addFacultyButton)))))
                .addContainerGap(175, Short.MAX_VALUE))
        );
        addFacultyPanelLayout.setVerticalGroup(
            addFacultyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addFacultyPanelLayout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addGroup(addFacultyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(addFacultyTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addComponent(addFacultyButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(addFacultyStatusLabel)
                .addContainerGap(252, Short.MAX_VALUE))
        );

        facultyStatusPanel.addTab("Add Faculty", addFacultyPanel);

        FacultyReserveLabel.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        FacultyReserveLabel.setText("Faculty: ");

        DateReserveLabel.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        DateReserveLabel.setText("Date: ");

        SeatsReserveLabel.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        SeatsReserveLabel.setText("Seats Required: ");

        reserveSeatsTextField.setColumns(3);

        reserveSubmitButton.setText("Submit");
        reserveSubmitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reserveSubmitButtonActionPerformed(evt);
            }
        });

        reserveStatusLabel.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        reserveStatusLabel.setText("   ");

        reservationByDateScrollPanel.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        reservationByDateList.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        reservationByDateList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        reservationByDateScrollPanel.setViewportView(reservationByDateList);

        reservationRecordLabel.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        reservationRecordLabel.setText("  Reservations Record");

        dateReserveLabel.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        dateReserveLabel.setText("Date: ");

        searchReserveButton.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        searchReserveButton.setText("Search");
        searchReserveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchReserveButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addComponent(reservationRecordLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(58, 58, 58)
                                .addComponent(searchReserveButton))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(dateReserveLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(dateReserveComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(0, 12, Short.MAX_VALUE))
                    .addComponent(reservationByDateScrollPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(reservationRecordLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(reservationByDateScrollPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dateReserveLabel)
                    .addComponent(dateReserveComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addComponent(searchReserveButton)
                .addContainerGap(108, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout reserveRoomPanelLayout = new javax.swing.GroupLayout(reserveRoomPanel);
        reserveRoomPanel.setLayout(reserveRoomPanelLayout);
        reserveRoomPanelLayout.setHorizontalGroup(
            reserveRoomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(reserveRoomPanelLayout.createSequentialGroup()
                .addGroup(reserveRoomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(reserveRoomPanelLayout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addGroup(reserveRoomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(FacultyReserveLabel)
                            .addComponent(DateReserveLabel)
                            .addComponent(SeatsReserveLabel))
                        .addGap(18, 18, 18)
                        .addGroup(reserveRoomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(reserveFacultyComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(reserveDateComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(reserveSeatsTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(reserveRoomPanelLayout.createSequentialGroup()
                        .addGap(116, 116, 116)
                        .addComponent(reserveSubmitButton))
                    .addGroup(reserveRoomPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(reserveStatusLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 406, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );
        reserveRoomPanelLayout.setVerticalGroup(
            reserveRoomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(reserveRoomPanelLayout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(reserveRoomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(FacultyReserveLabel)
                    .addComponent(reserveFacultyComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(reserveRoomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(reserveDateComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DateReserveLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(reserveRoomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SeatsReserveLabel)
                    .addComponent(reserveSeatsTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addComponent(reserveSubmitButton)
                .addGap(31, 31, 31)
                .addComponent(reserveStatusLabel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, reserveRoomPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        facultyStatusPanel.addTab("Reserve Room", reserveRoomPanel);

        addRoomLabel.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        addRoomLabel.setText("Add Room");

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel4.setText("Room Name: ");

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel5.setText("Seats: ");

        dropRoomLabel.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        dropRoomLabel.setText("Drop Room");

        dropRoomButton.setText("Drop");
        dropRoomButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dropRoomButtonActionPerformed(evt);
            }
        });

        addRoomButton.setText("Add");
        addRoomButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addRoomButtonActionPerformed(evt);
            }
        });

        dropRoomList.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        dropRoomList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane5.setViewportView(dropRoomList);

        addRoomList.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        addRoomList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane6.setViewportView(addRoomList);

        javax.swing.GroupLayout AddDropRoomPanelLayout = new javax.swing.GroupLayout(AddDropRoomPanel);
        AddDropRoomPanel.setLayout(AddDropRoomPanelLayout);
        AddDropRoomPanelLayout.setHorizontalGroup(
            AddDropRoomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AddDropRoomPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 316, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(AddDropRoomPanelLayout.createSequentialGroup()
                .addGroup(AddDropRoomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(AddDropRoomPanelLayout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addGroup(AddDropRoomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(addRoomLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(AddDropRoomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(seatsTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
                            .addComponent(roomNameTextField))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(AddDropRoomPanelLayout.createSequentialGroup()
                        .addGap(101, 101, 101)
                        .addComponent(addRoomButton, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(57, 57, 57)
                        .addComponent(addDropRoomLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(37, 37, 37)))
                .addGroup(AddDropRoomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(AddDropRoomPanelLayout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(dropRoomButton, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(dropRoomComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dropRoomLabel))
                .addGap(97, 97, 97))
        );
        AddDropRoomPanelLayout.setVerticalGroup(
            AddDropRoomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AddDropRoomPanelLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(AddDropRoomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(AddDropRoomPanelLayout.createSequentialGroup()
                        .addGroup(AddDropRoomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(addRoomLabel)
                            .addComponent(dropRoomLabel))
                        .addGap(28, 28, 28)
                        .addGroup(AddDropRoomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(AddDropRoomPanelLayout.createSequentialGroup()
                                .addGroup(AddDropRoomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel4)
                                    .addComponent(roomNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(24, 24, 24)
                                .addGroup(AddDropRoomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel5)
                                    .addComponent(seatsTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(addRoomButton))
                            .addComponent(addDropRoomLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(AddDropRoomPanelLayout.createSequentialGroup()
                        .addComponent(dropRoomComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(67, 67, 67)
                        .addComponent(dropRoomButton)))
                .addGap(24, 24, 24)
                .addGroup(AddDropRoomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane5)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        facultyStatusPanel.addTab("Add & Drop Room", AddDropRoomPanel);

        jScrollPane3.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        waitlistList.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jScrollPane3.setViewportView(waitlistList);

        waitlistLabel.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        waitlistLabel.setText("Faculties in Waitlist");

        refreshWaitlistButton.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        refreshWaitlistButton.setText("Refresh");
        refreshWaitlistButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshWaitlistButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout waitlistPanelLayout = new javax.swing.GroupLayout(waitlistPanel);
        waitlistPanel.setLayout(waitlistPanelLayout);
        waitlistPanelLayout.setHorizontalGroup(
            waitlistPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, waitlistPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(waitlistLabel)
                .addGap(227, 227, 227))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, waitlistPanelLayout.createSequentialGroup()
                .addGroup(waitlistPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(waitlistPanelLayout.createSequentialGroup()
                        .addGap(283, 283, 283)
                        .addComponent(refreshWaitlistButton)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(waitlistPanelLayout.createSequentialGroup()
                        .addGap(0, 123, Short.MAX_VALUE)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 431, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(99, 99, 99))
        );
        waitlistPanelLayout.setVerticalGroup(
            waitlistPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(waitlistPanelLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(waitlistLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addComponent(refreshWaitlistButton)
                .addContainerGap(57, Short.MAX_VALUE))
        );

        facultyStatusPanel.addTab("Waitlist", waitlistPanel);

        yearLabel.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        yearLabel.setText("Year: ");

        monthLabel.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        monthLabel.setText("Month: ");

        dayLabel.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        dayLabel.setText("Day:");

        addDateSubmitButton.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        addDateSubmitButton.setText("Submit");
        addDateSubmitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addDateSubmitButtonActionPerformed(evt);
            }
        });

        addDateStatusLabel.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N

        yearComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025" }));

        dayComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));

        monthComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));

        jLabel7.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N

        jLabel8.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N

        javax.swing.GroupLayout addDatePanelLayout = new javax.swing.GroupLayout(addDatePanel);
        addDatePanel.setLayout(addDatePanelLayout);
        addDatePanelLayout.setHorizontalGroup(
            addDatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, addDatePanelLayout.createSequentialGroup()
                .addContainerGap(235, Short.MAX_VALUE)
                .addGroup(addDatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(addDatePanelLayout.createSequentialGroup()
                        .addGroup(addDatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(yearLabel)
                            .addComponent(monthLabel)
                            .addComponent(dayLabel))
                        .addGap(37, 37, 37)
                        .addGroup(addDatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(yearComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(monthComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(dayComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(addDatePanelLayout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(addDateSubmitButton)))
                .addGap(228, 228, 228))
            .addComponent(addDateStatusLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(addDatePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(addDatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(addDatePanelLayout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        addDatePanelLayout.setVerticalGroup(
            addDatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addDatePanelLayout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(addDatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(yearLabel)
                    .addComponent(yearComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(addDatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(monthLabel)
                    .addComponent(monthComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(addDatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dayLabel)
                    .addComponent(dayComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addComponent(addDateSubmitButton)
                .addGap(18, 18, 18)
                .addComponent(addDateStatusLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(72, Short.MAX_VALUE))
        );

        facultyStatusPanel.addTab("Add Date", addDatePanel);

        cancelResFacultyLabel.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        cancelResFacultyLabel.setText("Faculty: ");

        cancelResDateLabel.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        cancelResDateLabel.setText("Date:");

        cancelResSubmitButton.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        cancelResSubmitButton.setText("Submit");
        cancelResSubmitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelResSubmitButtonActionPerformed(evt);
            }
        });

        cancelResStatusLabel.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N

        resStatusLabel.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N

        waitlistStatusLabel.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cancelResFacultyLabel)
                                    .addComponent(cancelResDateLabel))
                                .addGap(40, 40, 40)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(dateResComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(facultyResComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(61, 61, 61)
                                .addComponent(cancelResSubmitButton)))
                        .addGap(0, 380, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(cancelResStatusLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(waitlistStatusLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(resStatusLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancelResFacultyLabel)
                    .addComponent(facultyResComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancelResDateLabel)
                    .addComponent(dateResComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50)
                .addComponent(cancelResSubmitButton)
                .addGap(34, 34, 34)
                .addComponent(cancelResStatusLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(resStatusLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(waitlistStatusLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(87, Short.MAX_VALUE))
        );

        facultyStatusPanel.addTab("Cancel Reserve", jPanel1);

        facultyStatusLabel.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        facultyStatusLabel.setText("Faculty: ");

        facultyStatusSubmitButton.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        facultyStatusSubmitButton.setText("Submit");
        facultyStatusSubmitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                facultyStatusSubmitButtonActionPerformed(evt);
            }
        });

        jScrollPane2.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        reservedRoomList.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        reservedRoomList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane2.setViewportView(reservedRoomList);

        jScrollPane4.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        waitlistRoomList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane4.setViewportView(waitlistRoomList);

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel3.setText("Room Reserved by Faculty ");

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel6.setText("Room Waitlisted by Faculty");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(facultyStatusLabel)
                        .addGap(18, 18, 18)
                        .addComponent(facultyStatusComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(facultyStatusSubmitButton)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 58, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 375, Short.MAX_VALUE)
                    .addComponent(jScrollPane2))
                .addContainerGap(17, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(105, 105, 105))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(108, 108, 108))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(facultyStatusLabel)
                            .addComponent(facultyStatusComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(39, 39, 39)
                        .addComponent(facultyStatusSubmitButton))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34))
        );

        facultyStatusPanel.addTab("Status", jPanel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(facultyStatusPanel)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(190, 190, 190)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(32, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(facultyStatusPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 456, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addFacultyButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addFacultyButtonActionPerformed
        // Add the faculty to the faculty table.
        String name = addFacultyTextField.getText();
        Faculty.addFaculty(name);
        addFacultyStatusLabel.setText(name + " has been added to the Faculty.");
        
        // rebuild the reservation faculty combo box.
        rebuildFacultyComboBoxes();
    }//GEN-LAST:event_addFacultyButtonActionPerformed

    private void refreshWaitlistButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshWaitlistButtonActionPerformed
        // refresh waitlist button 
        rebuildWaitlistList();
    }//GEN-LAST:event_refreshWaitlistButtonActionPerformed

    private void addRoomButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addRoomButtonActionPerformed
        // add room to ROOMS
        // find all waitlist entry by room size - ArrayList
        // if room is available on a date, then add faculty into RESERVATIONS and delete WAITLIST
        // change status label
        try {
            String name = roomNameTextField.getText();
            if (RoomEntry.getRooms().contains(name)) { // make sure no identical room is added
                throw new IllegalArgumentException();
            }
            int seats = Integer.parseInt(seatsTextField.getText());
            ArrayList<String> infoList = new ArrayList<>();
            infoList.add("Room " + name + " with " + seats + " seats has been added into database");
            RoomQueries.addRoom(name, seats);
            ArrayList<WaitlistEntry> waitlistEntry = WaitlistQueries.getWaitlistBySeats(seats);
            for (WaitlistEntry entry : waitlistEntry) {
                if (!ReservationQueries.roomHasReserved(name, entry.getDate()) && !ReservationQueries.reservedOnDate(entry.getFaculty(), entry.getDate())) {
                    ReservationQueries.addReservationEntry(new ReservationEntry(entry.getFaculty(), name, entry.getDate(), entry.getSeats()));
                    WaitlistQueries.deleteWaitlistEntry(entry.getFaculty(), entry.getDate());
                    infoList.add(entry.getFaculty() + " reserved room " + name + " on " + entry.getDate());
                }
            }
            rebuildAddRoomList(infoList);
            rebuildDropRoomComboBox();
        } catch (Exception e) {
            addDropRoomLabel.setText("Please fill out all the information correctly");
        }
    }//GEN-LAST:event_addRoomButtonActionPerformed

    private void addDateSubmitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addDateSubmitButtonActionPerformed
        // add a new date into database
        String year = yearComboBox.getItemAt(yearComboBox.getSelectedIndex()).toString();
        String month = monthComboBox.getItemAt(monthComboBox.getSelectedIndex()).toString();
        String day = dayComboBox.getItemAt(dayComboBox.getSelectedIndex()).toString();
        String date = year + "-" + month + "-" + day;
        Dates.addDate(date);
        rebuildDateComboBoxes();
        addDateStatusLabel.setText(date + " has been added into database");
    }//GEN-LAST:event_addDateSubmitButtonActionPerformed

    private void cancelResSubmitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelResSubmitButtonActionPerformed
        // cancel reservation
        String faculty = facultyResComboBox.getItemAt(facultyResComboBox.getSelectedIndex()).toString();
        String date = dateResComboBox.getItemAt(dateResComboBox.getSelectedIndex()).toString();
        boolean reservedOnDate = ReservationQueries.reservedOnDate(faculty, date);
        String resLabelString = "";
        String cancelResLabelString = "";
        String waitlistLabelString = "";
        if (reservedOnDate) { // check whether cancelation is for waitlist entry or reservation entry
            ReservationEntry reservationEntry = ReservationQueries.getReservationByFacultyAndDate(faculty, date); 
            WaitlistEntry waitlist = WaitlistQueries.getWaitlistBySeatsAndDate(reservationEntry.getSeats(), date);
            ReservationQueries.deleteReservation(reservationEntry.getRoom(), date);
            if (waitlist.getFaculty() != null && !ReservationQueries.reservedOnDate(waitlist.getFaculty(), waitlist.getDate())) {
                // move faculty from waitlist to reservation
                ReservationQueries.addReservationEntry(new ReservationEntry(waitlist.getFaculty(), reservationEntry.getRoom(), date, reservationEntry.getSeats()));
                WaitlistQueries.deleteWaitlistEntry(waitlist.getFaculty(), date);
                resLabelString = waitlist.getFaculty() + " has reserved room " + reservationEntry.getRoom() + " with " + reservationEntry.getSeats() + " seats on " + date;
            }
            cancelResLabelString = "Reservation entry for " + faculty + " on " + date + " has been canceled";
            
        } else { // cancel waitlist
            WaitlistQueries.deleteWaitlistEntry(faculty, date);
            waitlistLabelString = "Waitlist entry for " + faculty + " on " + date + " has been canceled";
        }
        // set status label
        resStatusLabel.setText(resLabelString);
        cancelResStatusLabel.setText(cancelResLabelString);
        waitlistStatusLabel.setText(waitlistLabelString);
    }//GEN-LAST:event_cancelResSubmitButtonActionPerformed

    private void facultyStatusSubmitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_facultyStatusSubmitButtonActionPerformed
        // rebuild faculty status list
        rebuildFacultyStatusList();
    }//GEN-LAST:event_facultyStatusSubmitButtonActionPerformed

    private void searchReserveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchReserveButtonActionPerformed
        // show message in reservation panel
        String date = dateReserveComboBox.getItemAt(dateReserveComboBox.getSelectedIndex()).toString();
        rebuildReservationByDateList(date);
    }//GEN-LAST:event_searchReserveButtonActionPerformed

    private void reserveSubmitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reserveSubmitButtonActionPerformed
        // add new reservations or waitlist
        try {
            String name = reserveFacultyComboBox.getItemAt(reserveFacultyComboBox.getSelectedIndex());
            String date = reserveDateComboBox.getItemAt(reserveDateComboBox.getSelectedIndex());
            int seats = Integer.parseInt(reserveSeatsTextField.getText());
            assignRoomToFaculty(name, date, seats);
        } catch (Exception e) {
            reserveStatusLabel.setText("Please fill out appropriate information.");
        }

    }//GEN-LAST:event_reserveSubmitButtonActionPerformed

    private void dropRoomButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dropRoomButtonActionPerformed
        // drop room button
        String room = dropRoomComboBox.getItemAt(dropRoomComboBox.getSelectedIndex()).toString();
        ArrayList<ReservationEntry> reservations = ReservationQueries.getReservationsByRoom(room);
        ArrayList<String> infoList = new ArrayList<>(); // list for displaying all operations
        RoomQueries.dropRoom(room);
        infoList.add("Room " + room + " has been dropped");
        // delete all reservation entries which reserved the room being dropped
        ReservationQueries.deleteReservation(room);
        // assign room to faculties, or move them into waitlist
        assignRoomToFaculty(reservations, infoList);
        rebuildDropRoomList(infoList);
    }//GEN-LAST:event_dropRoomButtonActionPerformed

    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[])
    {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try
        {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels())
            {
                if ("Nimbus".equals(info.getName()))
                {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex)
        {
            java.util.logging.Logger.getLogger(RoomSchedulerFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex)
        {
            java.util.logging.Logger.getLogger(RoomSchedulerFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex)
        {
            java.util.logging.Logger.getLogger(RoomSchedulerFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex)
        {
            java.util.logging.Logger.getLogger(RoomSchedulerFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                new RoomSchedulerFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel AddDropRoomPanel;
    private javax.swing.JLabel DateReserveLabel;
    private javax.swing.JLabel FacultyReserveLabel;
    private javax.swing.JLabel SeatsReserveLabel;
    private javax.swing.JPanel addDatePanel;
    private javax.swing.JLabel addDateStatusLabel;
    private javax.swing.JButton addDateSubmitButton;
    private javax.swing.JLabel addDropRoomLabel;
    private javax.swing.JButton addFacultyButton;
    private javax.swing.JPanel addFacultyPanel;
    private javax.swing.JLabel addFacultyStatusLabel;
    private javax.swing.JTextField addFacultyTextField;
    private javax.swing.JButton addRoomButton;
    private javax.swing.JLabel addRoomLabel;
    private javax.swing.JList addRoomList;
    private javax.swing.JLabel cancelResDateLabel;
    private javax.swing.JLabel cancelResFacultyLabel;
    private javax.swing.JLabel cancelResStatusLabel;
    private javax.swing.JButton cancelResSubmitButton;
    private javax.swing.JComboBox dateResComboBox;
    private javax.swing.JComboBox dateReserveComboBox;
    private javax.swing.JLabel dateReserveLabel;
    private javax.swing.JComboBox dayComboBox;
    private javax.swing.JLabel dayLabel;
    private javax.swing.JButton dropRoomButton;
    private javax.swing.JComboBox dropRoomComboBox;
    private javax.swing.JLabel dropRoomLabel;
    private javax.swing.JList dropRoomList;
    private javax.swing.JComboBox facultyResComboBox;
    private javax.swing.JComboBox facultyStatusComboBox;
    private javax.swing.JLabel facultyStatusLabel;
    private javax.swing.JTabbedPane facultyStatusPanel;
    private javax.swing.JButton facultyStatusSubmitButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JComboBox monthComboBox;
    private javax.swing.JLabel monthLabel;
    private javax.swing.JButton refreshWaitlistButton;
    private javax.swing.JLabel resStatusLabel;
    private javax.swing.JList reservationByDateList;
    private javax.swing.JScrollPane reservationByDateScrollPanel;
    private javax.swing.JLabel reservationRecordLabel;
    private javax.swing.JComboBox<String> reserveDateComboBox;
    private javax.swing.JComboBox<String> reserveFacultyComboBox;
    private javax.swing.JPanel reserveRoomPanel;
    private javax.swing.JTextField reserveSeatsTextField;
    private javax.swing.JLabel reserveStatusLabel;
    private javax.swing.JButton reserveSubmitButton;
    private javax.swing.JList reservedRoomList;
    private javax.swing.JTextField roomNameTextField;
    private javax.swing.JButton searchReserveButton;
    private javax.swing.JTextField seatsTextField;
    private javax.swing.JLabel waitlistLabel;
    private javax.swing.JList waitlistList;
    private javax.swing.JPanel waitlistPanel;
    private javax.swing.JList waitlistRoomList;
    private javax.swing.JLabel waitlistStatusLabel;
    private javax.swing.JComboBox yearComboBox;
    private javax.swing.JLabel yearLabel;
    // End of variables declaration//GEN-END:variables
}