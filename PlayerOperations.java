interface PlayerOperations {
    void addPlayer(Player player);
    void viewPlayers();
    void updatePlayer(int id, String name, int age, String position, String asalNegara, String status, String grade, double gaji);
    void deletePlayer(int id);
    double calculateTotalSalary();
}