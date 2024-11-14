/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package tugas.pkg4;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.Month;
import com.toedter.calendar.JCalendar;
import java.util.Calendar;
import java.util.Date;

public class AplikasiJumlahHari extends JFrame {
    private JComboBox<String> cmbBulan;
    private JSpinner spinnerTahun;
    private JLabel lblHasil;
    private JLabel lblInfo;
    private JCalendar calendar;

    public AplikasiJumlahHari() {
        setTitle("Aplikasi Jumlah Hari dalam Bulan");
        setSize(600, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // JComboBox untuk bulan
        String[] bulanOptions = {
            "Januari", "Februari", "Maret", "April", "Mei", "Juni",
            "Juli", "Agustus", "September", "Oktober", "November", "Desember"
        };
        cmbBulan = new JComboBox<>(bulanOptions);

        // JSpinner untuk tahun
        spinnerTahun = new JSpinner(new SpinnerNumberModel(LocalDate.now().getYear(), 1900, 2100, 1));
        spinnerTahun.setPreferredSize(new Dimension(80, 20));
        spinnerTahun.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                hitungJumlahHari(); // Update hasil saat tahun berubah
            }
        });

        // Button untuk menghitung jumlah hari
        JButton btnHitung = new JButton("Hitung");
        btnHitung.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hitungJumlahHari();
            }
        });

        // Button untuk menghitung selisih hari
        JButton btnSelisih = new JButton("Hitung Selisih Hari");
        btnSelisih.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hitungSelisihHari();
            }
        });

        // JCalendar untuk memilih bulan dan tahun
        calendar = new JCalendar();

        // Label untuk hasil
        lblHasil = new JLabel("Jumlah Hari: ");
        lblInfo = new JLabel("");

        // Menambahkan komponen ke GridBagLayout
        gbc.gridx = 0; gbc.gridy = 0; add(new JLabel("Pilih Bulan: "), gbc);
        gbc.gridx = 1; gbc.gridy = 0; add(cmbBulan, gbc);

        gbc.gridx = 0; gbc.gridy = 1; add(new JLabel("Tahun: "), gbc);
        gbc.gridx = 1; gbc.gridy = 1; add(spinnerTahun, gbc);

        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 2; add(btnHitung, gbc);
        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2; add(btnSelisih, gbc);

        gbc.gridx = 0; gbc.gridy = 4; gbc.gridwidth = 2; add(calendar, gbc);
        gbc.gridx = 0; gbc.gridy = 5; gbc.gridwidth = 2; add(lblHasil, gbc);
        gbc.gridx = 0; gbc.gridy = 6; gbc.gridwidth = 2; add(lblInfo, gbc);
    }

    private void hitungJumlahHari() {
        int bulanIndex = cmbBulan.getSelectedIndex();
        int tahun = (Integer) spinnerTahun.getValue();

        // Menghitung jumlah hari dalam bulan
        Month bulan = Month.of(bulanIndex + 1);
        int jumlahHari = bulan.length(LocalDate.of(tahun, bulan, 1).isLeapYear());

        // Hari pertama dan terakhir
        LocalDate hariPertama = LocalDate.of(tahun, bulan, 1);
        LocalDate hariTerakhir = hariPertama.withDayOfMonth(jumlahHari);

        // Menampilkan hasil
        lblHasil.setText("Jumlah Hari: " + jumlahHari);
        lblInfo.setText("Hari Pertama: " + hariPertama + ", Hari Terakhir: " + hariTerakhir);
    }

    private void hitungSelisihHari() {
        // Mendapatkan tanggal dari JCalendar
        Date tanggalDipilih = calendar.getDate();
        Calendar cal = Calendar.getInstance();
        cal.setTime(tanggalDipilih);
        LocalDate tanggalSatu = LocalDate.of(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1, cal.get(Calendar.DAY_OF_MONTH));

        // Tanggal kedua (misalnya, tanggal sekarang)
        LocalDate tanggalDua = LocalDate.now();

        long selisihHari = Math.abs(tanggalSatu.toEpochDay() - tanggalDua.toEpochDay());

        lblInfo.setText("Selisih hari antara tanggal dipilih dan sekarang: " + selisihHari + " hari");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AplikasiJumlahHari app = new AplikasiJumlahHari();
            app.setVisible(true);
        });
    }
}
