import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

class Drone {
    private String jenis;
    private String status;
    private double hargaSewa;
    private int resolusi;

    public Drone(String jenis, String status, double hargaSewa, int resolusi) {
        this.jenis = jenis;
        this.status = status;
        this.hargaSewa = hargaSewa;
        this.resolusi = resolusi;
    }

    public String getJenis() {
        return jenis;
    }

    public String getStatus() {
        return status;
    }

    public double getHargaSewa() {
        return hargaSewa;
    }

    public int getResolusi() {
        return resolusi;
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
}

public class MenuPendataan {
    private static ArrayList<Drone> drones = new ArrayList<>();
    private static ArrayList<Pelanggan> pelanggan = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            System.out.println("\n\nMenu Pendataan:");
            System.out.println("1. Pendataan Drone");
            System.out.println("2. Pendataan Pelanggan");
            System.out.println("3. Tampilkan Daftar Drone");
            System.out.println("4. Tampilkan Daftar Pelanggan");
            System.out.println("5. Keluar");
            System.out.print("Pilih menu: ");
            int menu = scanner.nextInt();
            scanner.nextLine(); // consume newline character

            switch (menu) {
                case 1:
                    pendataanDrone();
                    break;
                case 2:
                    pendataanPelanggan();
                    break;
                case 3:
                    tampilkanDaftarDrone();
                    break;
                case 4:
                    tampilkanDaftarPelanggan();
                    break;
                case 5:
                    running = false;
                    System.out.println("Terima kasih!");
                    break;
                default:
                    System.out.println("Menu tidak valid!");
                    break;
            }
        }
    }

    private static void pendataanDrone() {
        System.out.print("\nJenis Drone: ");
        String jenis = scanner.nextLine();
        System.out.print("Status Drone: ");
        String status = scanner.nextLine();
        System.out.print("Harga Sewa per Hari: ");
        double hargaSewa = scanner.nextDouble();
        System.out.print("Resolusi Drone: ");
        int resolusi = scanner.nextInt();

        Drone drone = new Drone(jenis, status, hargaSewa, resolusi);
        drones.add(drone);

        System.out.println("[update]: Data drone berhasil ditambahkan.");
    }

    private static void pendataanPelanggan() {
        System.out.print("\nNama Pelanggan: ");
        String nama = scanner.nextLine();
        System.out.print("Alamat: ");
        String alamat = scanner.nextLine();
        System.out.print("Nomor Telpon: ");
        String nomorTelpon = scanner.nextLine();
        System.out.print("Jumlah Deposit: ");
        double deposit = scanner.nextDouble();

        Pelanggan p = new Pelanggan(nama, alamat, nomorTelpon, deposit);
        pelanggan.add(p);

        System.out.println("[update]: Data pelanggan berhasil ditambahkan.");
    }

    private static void tampilkanDaftarDrone() {
        if (drones.isEmpty()) {
            System.out.println("\nBelum ada drone yang didata.");
            return;
        }

        System.out.println("\n\nDaftar Drone:");
        System.out.println("--------------------------------------------------------");
        System.out.printf("%-15s %-15s %-15s %-15s\n", "Jenis", "Status", "Harga Sewa", "Resolusi");
        System.out.println("--------------------------------------------------------");

        for (Drone drone : drones) {
            System.out.printf("%-15s %-15s %-15.2f %-15d\n",
                    drone.getJenis(), drone.getStatus(), drone.getHargaSewa(), drone.getResolusi());
        }

        System.out.println();

        System.out.println("Urutkan daftar drone berdasarkan harga sewa");
        System.out.print("Pilih urutan (kecil-besar [1] atau besar-kecil [2]): ");
        String urutan = scanner.nextLine();

        if (urutan.equalsIgnoreCase("1")) {
            Collections.sort(drones, new Comparator<Drone>() {
                @Override
                public int compare(Drone d1, Drone d2) {
                    return Double.compare(d1.getHargaSewa(), d2.getHargaSewa());
                }
            });
        } else if (urutan.equalsIgnoreCase("2")) {
            Collections.sort(drones, new Comparator<Drone>() {
                @Override
                public int compare(Drone d1, Drone d2) {
                    return Double.compare(d2.getHargaSewa(), d1.getHargaSewa());
                }
            });
        }

        System.out.println("\nDaftar Drone setelah diurutkan:");
        System.out.println("--------------------------------------------------------");
        System.out.printf("%-15s %-15s %-15s %-15s\n", "Jenis", "Status", "Harga Sewa", "Resolusi");
        System.out.println("--------------------------------------------------------");

        for (Drone drone : drones) {
            System.out.printf("%-15s %-15s %-15.2f %-15d\n",
                    drone.getJenis(), drone.getStatus(), drone.getHargaSewa(), drone.getResolusi());
        }

        System.out.println();
    }

    private static void tampilkanDaftarPelanggan() {
        if (pelanggan.isEmpty()) {
            System.out.println("\nBelum ada pelanggan yang didata.");
            return;
        }

        System.out.println("\n\nDaftar Pelanggan:");
        System.out.println("--------------------------------------------------------");
        System.out.printf("%-15s %-15s %-15s %-15s\n", "Nama", "Alamat", "Nomor Telpon", "Deposit");
        System.out.println("--------------------------------------------------------");

        for (Pelanggan p : pelanggan) {
            System.out.printf("%-15s %-15s %-15s %-15.2f\n",
                    p.getNama(), p.getAlamat(), p.getNomorTelpon(), p.getDeposit());
        }

        System.out.println();

        System.out.println("Urutkan daftar pelanggan berdasarkan nama");
        System.out.print("Pilih urutan (ASC [1] atau DSC [2]): ");
        String urutan = scanner.nextLine();

        if (urutan.equalsIgnoreCase("1")) {
            Collections.sort(pelanggan, new Comparator<Pelanggan>() {
                @Override
                public int compare(Pelanggan p1, Pelanggan p2) {
                    return p1.getNama().compareTo(p2.getNama());
                }
            });
        } else if (urutan.equalsIgnoreCase("2")) {
            Collections.sort(pelanggan, new Comparator<Pelanggan>() {
                @Override
                public int compare(Pelanggan p1, Pelanggan p2) {
                    return p2.getNama().compareTo(p1.getNama());
                }
            });
        }        

        System.out.println("\nDaftar Pelanggan setelah diurutkan:");
        System.out.println("--------------------------------------------------------");
        System.out.printf("%-15s %-15s %-15s %-15s\n", "Nama", "Alamat", "Nomor Telpon", "Deposit");
        System.out.println("--------------------------------------------------------");

        for (Pelanggan p : pelanggan) {
            System.out.printf("%-15s %-15s %-15s %-15.2f\n",
                    p.getNama(), p.getAlamat(), p.getNomorTelpon(), p.getDeposit());
        }

        System.out.println();
    }

}
