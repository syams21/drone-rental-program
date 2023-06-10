import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class SewaDrone {
    private static ArrayList<Drone> drones = new ArrayList<>();
    private static ArrayList<Pelanggan> pelanggan = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int pilihan;
        do {
            System.out.println("\n\nMenu:");
            System.out.println("1. Pendataan Drone");
            System.out.println("2. Pendataan Pelanggan");
            System.out.println("3. Sorting Drone");
            System.out.println("4. Sewa Drone");
            System.out.println("5. Pengembalian Drone");
            System.out.println("6. Keluar");
            System.out.print("Pilihan Anda: ");
            pilihan = scanner.nextInt();
            scanner.nextLine(); // Membersihkan newline character setelah membaca pilihan

            switch (pilihan) {
                case 1:
                    menuPendataanDrone();
                    break;
                case 2:
                    menuPendataanPelanggan();
                    break;
                case 3:
                    menuSorting();
                    break;
                case 4:
                    menuSewaDrone();
                    break;
                case 5:
                    menuPengembalianDrone();
                    break;
                case 6:
                    System.out.println("Terima kasih. Program selesai.");
                    break;
                default:
                    System.out.println("Pilihan tidak valid. Silakan pilih menu yang tersedia.");
                    break;
            }

            System.out.println();
        } while (pilihan != 5);
    }

    private static void menuPendataanDrone() {
        System.out.print("\nJenis Drone: ");
        String jenis = scanner.nextLine();
        System.out.print("Status Drone (available, sedang disewa, sedang dalam perbaikan): ");
        String status = scanner.nextLine();
        System.out.print("Harga Sewa per Hari: ");
        double hargaSewa = scanner.nextDouble();
        System.out.print("Resolusi Drone: ");
        int resolusi = scanner.nextInt();

        Drone drone = new Drone(jenis, status, hargaSewa, resolusi);
        drones.add(drone);

        System.out.println("[update] Data drone telah ditambahkan.");
    }

    private static void menuPendataanPelanggan() {
        System.out.print("\nNama: ");
        String nama = scanner.nextLine();
        System.out.print("Alamat: ");
        String alamat = scanner.nextLine();
        System.out.print("Nomor Telpon: ");
        String nomorTelpon = scanner.nextLine();
        System.out.print("Jumlah Deposit: ");
        double deposit = scanner.nextDouble();

        Pelanggan pelanggan = new Pelanggan(nama, alamat, nomorTelpon, deposit);
        SewaDrone.pelanggan.add(pelanggan);

        System.out.println("[update]Data pelanggan telah ditambahkan.");
    }

    private static void menuSewaDrone() {
        if (drones.isEmpty()) {
            System.out.println("Belum ada drone yang tersedia.");
            return;
        }

        System.out.println("]nDaftar Drone Tersedia:");
        tampilkanDaftarDrone();

        System.out.print("Pilih nomor drone yang ingin disewa: ");
        int nomorDrone = scanner.nextInt();
        scanner.nextLine(); // Membersihkan newline character setelah membaca nomor drone

        if (nomorDrone < 1 || nomorDrone > drones.size()) {
            System.out.println("Nomor drone tidak valid.");
            return;
        }

        Drone drone = drones.get(nomorDrone - 1);

        System.out.print("Nama Pelanggan: ");
        String namaPelanggan = scanner.nextLine();

        Pelanggan pelanggan = null;
        for (Pelanggan p : SewaDrone.pelanggan) {
            if (p.getNama().equalsIgnoreCase(namaPelanggan)) {
                pelanggan = p;
                break;
            }
        }

        if (pelanggan == null) {
            System.out.println("Pelanggan dengan nama tersebut tidak ditemukan.");
            return;
        }

        System.out.print("Lama Sewa (dalam hari): ");
        int lamaSewa = scanner.nextInt();

        double biayaSewa = drone.getHargaSewa() * lamaSewa;
        double totalBiaya = biayaSewa;

        System.out.println("\nDetail Penyewaan:");
        System.out.println("--------------------------------------------------------");
        System.out.println("Nama Pelanggan: " + pelanggan.getNama());
        System.out.println("Drone yang Disewa: " + drone.getJenis());
        System.out.println("Lama Sewa (hari): " + lamaSewa);
        System.out.println("Biaya Sewa per Hari: " + drone.getHargaSewa());
        System.out.println("Biaya Sewa Total: " + biayaSewa);
        System.out.println("--------------------------------------------------------");
        System.out.println("Total Biaya: " + totalBiaya);

        System.out.print("\nApakah pelanggan membayar deposit? (ya/tidak): ");
        String bayarDeposit = scanner.next();

        if (bayarDeposit.equalsIgnoreCase("ya")) {
            if (pelanggan.getDeposit() < totalBiaya) {
                System.out.println("Deposit tidak mencukupi. Pembayaran tidak dapat dilanjutkan.");
                return;
            }

            pelanggan.setDeposit(pelanggan.getDeposit() - totalBiaya);
            System.out.println("Pembayaran berhasil. Deposit pelanggan saat ini: " + pelanggan.getDeposit());
        } else {
            System.out.println("Pelanggan tidak membayar deposit. Pembayaran dilakukan secara tunai.");
        }

        drone.setStatus("sedang disewa");
    }

    private static void menuSorting() {
        System.out.println("\nMenu Sorting:");
        System.out.println("1. Sorting berdasarkan Jenis Drone");
        System.out.println("2. Sorting berdasarkan Status Drone");
        System.out.print("Pilihan Anda: ");
        int pilihan = scanner.nextInt();
        scanner.nextLine(); // Membersihkan newline character setelah membaca pilihan
    
        switch (pilihan) {
            case 1:
                Collections.sort(drones, new Comparator<Drone>() {
                    @Override
                    public int compare(Drone drone1, Drone drone2) {
                        return drone1.getJenis().compareToIgnoreCase(drone2.getJenis());
                    }
                });
                System.out.println("[update] Daftar drone telah diurutkan berdasarkan Jenis Drone.");
                break;
            case 2:
                Collections.sort(drones, new Comparator<Drone>() {
                    @Override
                    public int compare(Drone drone1, Drone drone2) {
                        return drone1.getStatus().compareToIgnoreCase(drone2.getStatus());
                    }
                });
                System.out.println("[update] Daftar drone telah diurutkan berdasarkan Status Drone.");
                break;
            default:
                System.out.println("Pilihan tidak valid. Silakan pilih menu yang tersedia.");
                break;
        }
    }
    

    private static void menuPengembalianDrone() {
        if (drones.isEmpty()) {
            System.out.println("Belum ada drone yang tersedia.");
            return;
        }

        System.out.println("Daftar Drone Tersedia:");
        tampilkanDaftarDrone();

        System.out.print("Pilih nomor drone yang dikembalikan: ");
        int nomorDrone = scanner.nextInt();
        scanner.nextLine(); // Membersihkan newline character setelah membaca nomor drone

        if (nomorDrone < 1 || nomorDrone > drones.size()) {
            System.out.println("Nomor drone tidak valid.");
            return;
        }

        Drone drone = drones.get(nomorDrone - 1);

        System.out.print("Kondisi Drone (baik/rusak): ");
        String kondisiDrone = scanner.nextLine();

        if (kondisiDrone.equalsIgnoreCase("rusak")) {
            double denda = drone.getHargaSewa() * 0.2;
            System.out.println("Drone yang dikembalikan rusak. Denda sebesar 20% dari harga sewa dikenakan.");
            System.out.println("Denda: " + denda);

            // Mengembalikan deposit pelanggan yang sudah dibayarkan
            Pelanggan pelanggan = null;
            for (Pelanggan p : SewaDrone.pelanggan) {
                if (p.getNama().equalsIgnoreCase(drone.getPelanggan())) {
                    pelanggan = p;
                    break;
                }
            }

            if (pelanggan != null) {
                pelanggan.setDeposit(pelanggan.getDeposit() + denda);
                System.out.println("Deposit pelanggan saat ini: " + pelanggan.getDeposit());
            }
        }

        drone.setStatus("available");
        drone.setPelanggan(null);

        System.out.println("Drone berhasil dikembalikan.");
    }

    private static void tampilkanDaftarDrone() {
        if (drones.isEmpty()) {
            System.out.println("Belum ada drone yang didata.");
            return;
        }

        System.out.println("--------------------------------------------------------");
        System.out.printf("%-5s %-15s %-15s %-15s\n", "No", "Jenis", "Status", "Harga Sewa");
        System.out.println("--------------------------------------------------------");

        for (int i = 0; i < drones.size(); i++) {
            Drone drone = drones.get(i);
            System.out.printf("%-5d %-15s %-15s %-15.2f\n",
                    (i + 1), drone.getJenis(), drone.getStatus(), drone.getHargaSewa());
        }
    }
}

class Drone {
    private String jenis;
    private String status;
    private double hargaSewa;
    private int resolusi;
    private String pelanggan;

    public Drone(String jenis, String status, double hargaSewa, int resolusi) {
        this.jenis = jenis;
        this.status = status;
        this.hargaSewa = hargaSewa;
        this.resolusi = resolusi;
        this.pelanggan = null;
    }

    public String getJenis() {
        return jenis;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getHargaSewa() {
        return hargaSewa;
    }

    public int getResolusi() {
        return resolusi;
    }

    public String getPelanggan() {
        return pelanggan;
    }

    public void setPelanggan(String pelanggan) {
        this.pelanggan = pelanggan;
    }
}

class Pelanggan {
    private String nama;
    private String alamat;
    private String nomorTelpon;
    private double deposit;

    public Pelanggan(String nama, String alamat, String nomorTelpon, double deposit) {
        this.nama = nama;
        this.alamat = alamat;
        this.nomorTelpon = nomorTelpon;
        this.deposit = deposit;
    }

    public String getNama() {
        return nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public String getNomorTelpon() {
        return nomorTelpon;
    }

    public double getDeposit() {
        return deposit;
    }

    public void setDeposit(double deposit) {
        this.deposit = deposit;
    }
}
