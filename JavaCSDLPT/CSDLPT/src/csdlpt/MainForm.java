package csdlpt;

import java.awt.*;
import java.io.File;
import javax.swing.*;
import javax.swing.border.*;

/**
 * Màn hình chính của hệ thống Quản lý Điện Lực Phân Tán
 * Gồm 5 tab chính: Chi Nhánh, Nhân Viên, Khách Hàng, Hợp Đồng, Hóa Đơn
 *
 * @author duc18
 */
public class MainForm extends JFrame {

    private DataModel dataModel;
    private File ipFile;

    private ChiNhanh tabChiNhanh;
    private NhanVien tabNhanVien;
    private KhachHang tabKhachHang;
    private HopDong tabHopDong;
    private HoaDon tabHoaDon;

    private JTabbedPane tabbedPane;
    private JTextArea logArea;
    private JScrollPane logScrollPane;


    private static final Color COLOR_PRIMARY = new Color(41, 128, 185);
    private static final Color COLOR_BACKGROUND = new Color(245, 245, 245);
    private static final Color COLOR_LOG_BG = new Color(0,0,0);
    private static final Color COLOR_LOG_TEXT = new Color(255, 255,255);


    public MainForm() {
        setLookAndFeel();

        // Khởi tạo logic
        dataModel = new DataModel();
        ipFile = new File("D:\\Netbean\\CSDLPTDOANTH\\IP.txt"); // ⚠️ Cập nhật đường dẫn IP nếu cần

        // Khởi tạo giao diện
        initUI();
        applyModernUI();

        // Gán logic cho từng tab
        initTabsWithLogic();

        // Kiểm tra file IP
        showStartupStatus();
    }

    private void initUI() {
        setTitle(" PHÂN TÁN ");
        setLayout(new BorderLayout());
        setSize(1200, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // --- Tabs ---
        tabbedPane = new JTabbedPane();
        tabbedPane.setFont(new Font("Segoe UI", Font.BOLD, 13));

        // Khởi tạo 5 panel
        tabChiNhanh = new ChiNhanh();
        tabNhanVien = new NhanVien();
        tabKhachHang = new KhachHang();
        tabHopDong = new HopDong();
        tabHoaDon = new HoaDon();

        // Thêm panel vào Tab
        tabbedPane.addTab(" Chi Nhánh", tabChiNhanh);
        tabbedPane.addTab(" Nhân Viên", tabNhanVien);
        tabbedPane.addTab(" Khách Hàng", tabKhachHang);
        tabbedPane.addTab(" Hợp Đồng", tabHopDong);
        tabbedPane.addTab(" Hóa Đơn", tabHoaDon);
        add(tabbedPane, BorderLayout.CENTER);

        // --- Log ---
        logArea = new JTextArea(10, 20);
        logArea.setEditable(false);
        logArea.setFont(new Font("Consolas", Font.PLAIN, 13));
        logArea.setLineWrap(true);
        logArea.setWrapStyleWord(true);

        logScrollPane = new JScrollPane(logArea);
        add(logScrollPane, BorderLayout.SOUTH);
    }

    private void applyModernUI() {
        getContentPane().setBackground(COLOR_BACKGROUND);

        // Log area style
        logArea.setBackground(COLOR_LOG_BG);
        logArea.setForeground(COLOR_LOG_TEXT);
        logArea.setCaretColor(Color.WHITE);

        logScrollPane.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(100, 100, 100), 1),
                " LOG",
                TitledBorder.LEFT, TitledBorder.TOP,
                new Font("Segoe UI", Font.BOLD, 12),
                new Color(200, 200, 200)
        ));
        logScrollPane.setPreferredSize(new Dimension(0, 200));
    }

    private void initTabsWithLogic() {
        tabChiNhanh.init(dataModel, ipFile, logArea);
        tabNhanVien.init(dataModel, ipFile, logArea);
        tabKhachHang.init(dataModel, ipFile, logArea);
        tabHopDong.init(dataModel, ipFile, logArea);
        tabHoaDon.init(dataModel, ipFile, logArea);
    }


    private void showStartupStatus() {
        if (!ipFile.exists()) {
            log(" Không tìm thấy file cấu hình IP.txt tại: " + ipFile.getAbsolutePath());
            log(" Vui lòng kiểm tra lại đường dẫn file IP!");
        } else {
            log(" HỆ THỐNG ĐÃ SẴN SÀNG");
            log("File IP: " + ipFile.getAbsolutePath());
            log(" Vui lòng chọn tab và tải dữ liệu để bắt đầu...");
        }
    }


    private void log(String message) {
        logArea.append(message + "\n");
    }


    private void setLookAndFeel() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainForm().setVisible(true));
    }
}
