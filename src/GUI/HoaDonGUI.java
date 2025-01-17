package GUI;

import BUS.SellTicketBus;
import DTO.HoaDon;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HoaDonGUI {
    private JPanel main;
    private JTable tblCheckOut;
    private JButton btnRemove;
    private JButton btnCapNhat;
    private JButton THÀNHCÔNGButton;
    private JButton btnAdd;
    private JButton bookDeleteAllButton;
    private JButton btnFilter;
    private JTabbedPane tab;
    private JButton btnXoaMaHD;
    private JComboBox cboMaHD;
    private JComboBox cboMaNV;
    private JComboBox cboMaKH;
    private JButton btnThongKe;
    private String employeeId;

    DefaultTableModel dtm = new DefaultTableModel();
    public static List<HoaDon> dshd = new ArrayList<>();
    SellTicketBus bus = new SellTicketBus();

    public HoaDonGUI(String employeeId) {
        this.employeeId = employeeId;
        initTable();
        initTabPane();
        tblCheckOut.setDefaultEditor(Object.class, null);
        tblCheckOut.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1) {
                    System.out.println("double clicked");
                    int[] pos = {tblCheckOut.getSelectedRow(), tblCheckOut.getSelectedColumn()};
                    String maHD = String.valueOf(tblCheckOut.getValueAt(pos[0],0)) ;
                    //Khoi tao
                    var cthd = new CTHDGUI(maHD,HoaDonGUI.this);
                    JFrame frame = new JFrame("ChiTietHoaDon");
                    frame.setContentPane(cthd.getMain());
                    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    frame.pack();
                    frame.setLocationRelativeTo(null);
                    frame.setVisible(true);
                }
            }
        });
        //khi thay doi tab

        tab.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (tab.getSelectedIndex() == 0) { // Check if maHD tab is selected
                    // Clear existing items in maHDComboBox

                    cboMaHD.removeAllItems();

                    // Call getAllMaHD method from SellTicketBus and add the returned values to maHDComboBox
                    List<String> maHDs = bus.getAllMaHD();
                    for (String maHD : maHDs) {
                        cboMaHD.addItem(maHD);
                    }
                    rmvListenerBtnFilter();
                    btnFilter.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            String selected = String.valueOf(cboMaHD.getSelectedItem()) ;
                            changeTable(bus.filterMaHD(selected));
                        }

                    });

                }
                else if (tab.getSelectedIndex() == 1) { // Check if maHD tab is selected
                    // Clear existing items in maHDComboBox
                    cboMaNV.removeAllItems();
                    // Call getAllMaHD method from SellTicketBus and add the returned values to maHDComboBox
                    List<HoaDon> dshd = bus.getAllSellTicket();
                    Map<String, String> mapNV = new HashMap<>();
                    for(HoaDon hd:dshd){
                        mapNV.put(hd.getTenNV(),hd.getMa_nv());
                    }
                    for(String tenNV:mapNV.keySet()){
                        cboMaNV.addItem(tenNV);
                    }
                    rmvListenerBtnFilter();
                    btnFilter.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            String selected = String.valueOf(cboMaNV.getSelectedItem()) ;
                            String idNV = mapNV.get(selected);
                            changeTable(bus.filterMaNV(idNV));
                        }
                    });
                }
                else if (tab.getSelectedIndex() == 2) { // Check if maHD tab is selected
                    // Clear existing items in maHDComboBox
                    cboMaKH.removeAllItems();
                    // Call getAllMaHD method from SellTicketBus and add the returned values to maHDComboBox
                    List<HoaDon> dshd = bus.getAllSellTicket();
                    Map<String, String> mapKH = new HashMap<>();
                    for(HoaDon hd:dshd){
                        mapKH.put(hd.getTenKH(),hd.getMa_KH());
                    }
                    for(String tenKH:mapKH.keySet()){
                        cboMaKH.addItem(tenKH);
                    }
                    rmvListenerBtnFilter();
                    btnFilter.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            String selected = String.valueOf(cboMaKH.getSelectedItem()) ;
                            String idKH = mapKH.get(selected);
                            changeTable(bus.filterMaKH(idKH));
                        }
                    });


                }
            }
        });

        //action listerner lọc

        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                var dialog = new HoaDonFD(HoaDonGUI.this, employeeId);
                dialog.pack();
                dialog.setLocationRelativeTo(null);
                dialog.setVisible(true);
            }
        });

        btnRemove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int[] pos = {tblCheckOut.getSelectedRow(), 0};
                if(pos[0]==-1){
                    JOptionPane.showMessageDialog(null,"Hãy chọn hóa đơn muốn xóa");
                }
                else{
                    int dialogResult = JOptionPane.showConfirmDialog(null,"Bạn có muốn xóa không ?","Remove", JOptionPane.YES_NO_OPTION);
                    if(dialogResult == JOptionPane.YES_OPTION){
                        String maHD = String.valueOf(tblCheckOut.getValueAt(pos[0],0)) ;
                        int smt =bus.remove(maHD);
                        if(smt>0){
                            JOptionPane.showMessageDialog(null,"Xóa hóa đơn thành công");
                            showAll();
                        }
                        else{
                            JOptionPane.showMessageDialog(null,"Xóa hóa đơn thất bại");
                        }
                    }
                }
            }
        });
        btnCapNhat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int[] pos = {tblCheckOut.getSelectedRow(), tblCheckOut.getSelectedColumn()};
                if(pos[0]==-1){
                    JOptionPane.showMessageDialog(null,"Hãy chọn hóa đơn muốn cập nhật");
                }
                else {
                    String maHD = String.valueOf(tblCheckOut.getValueAt(pos[0],0));
                    var dialog = new HoaDonFD(maHD,HoaDonGUI.this);
                    dialog.pack();
                    dialog.setLocationRelativeTo(null);
                    dialog.setVisible(true);
                }

            }
        });
        bookDeleteAllButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dshd =  bus.getAllSellTicket();
                changeTable(dshd);
            }
        });

    }

    public JPanel getMain() {
        return main;
    }

    public void setMain(JPanel main) {
        this.main = main;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("HoaDonGUI");
        frame.setContentPane(new HoaDonGUI("NV001").main);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    private void rmvListenerBtnFilter(){
        // Xóa tất cả các sự kiện trên JButton
        ActionListener[] listeners = btnFilter.getActionListeners();
        for (ActionListener listener : listeners) {
            btnFilter.removeActionListener(listener);
        }
    }
    private void initTabPane(){
        if (tab.getSelectedIndex() == 0) { // Check if maHD tab is selected
            // Clear existing items in maHDComboBox

            cboMaHD.removeAllItems();

            // Call getAllMaHD method from SellTicketBus and add the returned values to maHDComboBox
            List<String> maHDs = bus.getAllMaHD();
            for (String maHD : maHDs) {
                cboMaHD.addItem(maHD);
            }
            rmvListenerBtnFilter();
            btnFilter.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String selected = String.valueOf(cboMaHD.getSelectedItem()) ;
                    changeTable(bus.filterMaHD(selected));
                }

            });

        }
        else if (tab.getSelectedIndex() == 1) { // Check if maHD tab is selected
            // Clear existing items in maHDComboBox
            cboMaNV.removeAllItems();
            // Call getAllMaHD method from SellTicketBus and add the returned values to maHDComboBox
            List<HoaDon> dshd = bus.getAllSellTicket();
            Map<String, String> mapNV = new HashMap<>();
            for(HoaDon hd:dshd){
                mapNV.put(hd.getTenNV(),hd.getMa_nv());
            }
            for(String tenNV:mapNV.keySet()){
                cboMaNV.addItem(tenNV);
            }
            rmvListenerBtnFilter();
            btnFilter.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String selected = String.valueOf(cboMaNV.getSelectedItem()) ;
                    String idNV = mapNV.get(selected);
                    changeTable(bus.filterMaNV(idNV));
                }
            });
        }
        else if (tab.getSelectedIndex() == 2) { // Check if maHD tab is selected
            // Clear existing items in maHDComboBox
            cboMaKH.removeAllItems();
            // Call getAllMaHD method from SellTicketBus and add the returned values to maHDComboBox
            List<HoaDon> dshd = bus.getAllSellTicket();
            Map<String, String> mapKH = new HashMap<>();
            for(HoaDon hd:dshd){
                mapKH.put(hd.getTenKH(),hd.getMa_KH());
            }
            for(String tenKH:mapKH.keySet()){
                cboMaKH.addItem(tenKH);
            }
            rmvListenerBtnFilter();
            btnFilter.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String selected = String.valueOf(cboMaKH.getSelectedItem()) ;
                    String idKH = mapKH.get(selected);
                    changeTable(bus.filterMaKH(idKH));
                }
            });


        }
    }

    private void changeTable(List<HoaDon> dshd){
        dtm.setRowCount(0);
        String[] columns = {"Mã phiếu bán", "Mã nhân viên","Tên nhân viên", "Mã khách hàng","Tên khách hàng", "Ngày mua", "Tổng tiền"};
        dtm.setColumnIdentifiers(columns);
        if(!dshd.isEmpty()){
            for(HoaDon hd:dshd){
                Object[] t = {hd.getMa_phieu(), hd.getMa_nv(),hd.getTenNV(), hd.getMa_KH(),hd.getTenKH(),hd.getCreatedAt(),hd.getTongHD()};
                dtm.addRow(t);
            }
        }

        tblCheckOut.setModel(dtm);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < tblCheckOut.getColumnCount(); i++) {
            tblCheckOut.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    }
    private void initTable(){
        String[] columns = {"Mã phiếu bán", "Mã nhân viên","Tên nhân viên", "Mã khách hàng","Tên khách hàng", "Ngày mua","Tổng tiền"};
        dtm.setColumnIdentifiers(columns);

        dshd =  bus.getAllSellTicket();
        if(!dshd.isEmpty()){
            for(HoaDon hd:dshd){
                Object[] t = {hd.getMa_phieu(), hd.getMa_nv(),hd.getTenNV(), hd.getMa_KH(),hd.getTenKH(),hd.getCreatedAt(),hd.getTongHD()};
                dtm.addRow(t);
            }
        }
        tblCheckOut.setModel(dtm);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < tblCheckOut.getColumnCount(); i++) {
            tblCheckOut.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    }
    public void showAll(){
        changeTable(bus.getAllSellTicket());
        initTabPane();
    }
}