package GUI;

import BUS.CTHDBus;
import DAO.SellTicketDao;
import DTO.CTHD;

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
import java.util.List;

public class CTHDGUI {
    private JPanel main;
    private JButton btnRemove;
    private JButton btnUpdate;
    private JTable tblCTHD;
    private JButton bookDeleteAllButton;
    private JButton btnFilter;
    private JTabbedPane tab;
    private JComboBox cboMaCTHD;
    private JComboBox cboMaHD;
    private JComboBox cboHeSo;
    private JComboBox cboMaSeri;
    private JButton btnAdd;
    private JTextField txtTongHD;
    private JButton btnXuatPDF;
    private String maHD;
    DefaultTableModel dtm = new DefaultTableModel();
    CTHDBus bus = new CTHDBus();

    public static List<CTHD> dsCTHD = new ArrayList<>();


    public CTHDGUI(String maHD,HoaDonGUI guiHd) {
        tblCTHD.setDefaultEditor(Object.class, null);
        this.maHD = maHD;
        initTable();
        tab.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (tab.getSelectedIndex() == 0) {
                    cboHeSo.removeAllItems();
                    List<Double> heSo = bus.getAllHeSo(maHD);
                    for (Double so : heSo) {
                        cboHeSo.addItem(so);
                    }
//                    btnFilter.removeActionListener(btnFilter.getActionListeners()[0]);
                    rmvListerBtnFilter();

                    btnFilter.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            Double selected = Double.valueOf(cboHeSo.getSelectedItem().toString());
                            dsCTHD = bus.filterHeSo(maHD, selected);
                            changeTable();
                        }
                    });
                }

                else if (tab.getSelectedIndex() == 1) {
                    cboMaSeri.removeAllItems();
                    List<String> maSeries = bus.getAllMaSeries(maHD);
                    for (String seri : maSeries) {
                        cboMaSeri.addItem(seri);
                    }
                    rmvListerBtnFilter();

                    btnFilter.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            String selected = cboMaSeri.getSelectedItem().toString();
                            dsCTHD = bus.filterMaSeri(maHD, selected);
                            changeTable();
                        }
                    });
                }
            }
        });
        initTab();
        tblCTHD.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1) {
                    System.out.println("double clicked");
                    int[] pos = {tblCTHD.getSelectedRow(), tblCTHD.getSelectedColumn()};
                    String maHD = String.valueOf(tblCTHD.getValueAt(pos[0],0)) ;
                    //Khoi tao
                }
            }
        });
        btnRemove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int[] pos = {tblCTHD.getSelectedRow(), tblCTHD.getSelectedColumn()};
                if(tblCTHD.getSelectedRow()!=-1){
                    String maHD = String.valueOf(tblCTHD.getValueAt(pos[0],0)) ;
                    String maSeri = String.valueOf(tblCTHD.getValueAt(pos[0],2)) ;
                    int dialogResult = JOptionPane.showConfirmDialog(null,"Bạn có muốn xóa không ?","Remove", JOptionPane.YES_NO_OPTION);
                    if(dialogResult == JOptionPane.YES_OPTION) {
                        int smt = bus.remove(maHD, maSeri);
                        if (smt == 0) {
                            JOptionPane.showMessageDialog(null, "Xóa CTHD không thành công!");

                        } else {
                            JOptionPane.showMessageDialog(null, "Xóa CTHD thành công");
                            guiHd.showAll();
                            showAll();
                            bus.updateStatusBook(maSeri, "AVAILABLE");
                        }
                    }
                }
                else{
                    JOptionPane.showMessageDialog(null,"Chọn CTHD trước khi xóa");
                }

            }
        });

        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(tblCTHD.getSelectedRow()==-1){
                    JOptionPane.showMessageDialog(null,"Chọn CTHD để cập nhật");
                }
                else{
                    int[] pos = {tblCTHD.getSelectedRow(), tblCTHD.getSelectedColumn()};
                    String maSeri = String.valueOf(tblCTHD.getValueAt(pos[0],2)) ;
                    System.out.println(maSeri);
                    List<CTHD> ds = bus.filterMaCTHD(maHD,maSeri);
                    CTHD temp = ds.get(0);
                    CTHDFD dialog = new CTHDFD(temp,CTHDGUI.this,guiHd);
                    dialog.pack();
                    dialog.setLocationRelativeTo(null);
                    dialog.setVisible(true);
                }

            }
        });
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CTHDFD dialog = new CTHDFD(CTHDGUI.this,maHD,guiHd);
                dialog.pack();
                dialog.setLocationRelativeTo(null);
                dialog.setVisible(true);
            }
        });
        btnXuatPDF.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int i =  bus.xuatPDF(maHD);
                if(i==1){
                    JOptionPane.showMessageDialog(null,"Xuất PDF thành công");
                }
                else{
                    JOptionPane.showMessageDialog(null,"Xuất PDF thất bại");

                }
            }
        });
        bookDeleteAllButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showAll();
            }
        });
    }

    public void showAll(){
        dsCTHD = bus.getDsCTHD(maHD);
        changeTable();
        initTab();
    }

    private void changeTable(){
        dtm.setRowCount(0);
        String[] columns = {"Mã hóa đơn", "Hệ số","Mã series","Tên sách","Tổng tiền"};
        dtm.setColumnIdentifiers(columns);
        for (CTHD ct : dsCTHD) {
            Object[] t = { ct.getMa_phieu(), ct.getHe_so(), ct.getMa_series(),ct.getTenSach(),ct.getTienSach()};
            dtm.addRow(t);
        }
        tblCTHD.setModel(dtm);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < tblCTHD.getColumnCount(); i++) {
            tblCTHD.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    }

    private void rmvListerBtnFilter(){
        // Xóa tất cả các sự kiện trên JButton
        ActionListener[] listeners = btnFilter.getActionListeners();
        for (ActionListener listener : listeners) {
            btnFilter.removeActionListener(listener);
        }
    }
    private void initTab(){
        if (tab.getSelectedIndex() == 0) {
            cboHeSo.removeAllItems();
            List<Double> heSo = bus.getAllHeSo(maHD);
            for (Double so : heSo) {
                cboHeSo.addItem(so);
            }
//                    btnFilter.removeActionListener(btnFilter.getActionListeners()[0]);
            rmvListerBtnFilter();

            btnFilter.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Double selected = Double.valueOf(cboHeSo.getSelectedItem().toString());
                    dsCTHD = bus.filterHeSo(maHD, selected);
                    changeTable();
                }
            });
        }

        else if (tab.getSelectedIndex() == 1) {
            cboMaSeri.removeAllItems();
            List<String> maSeries = bus.getAllMaSeries(maHD);
            for (String seri : maSeries) {
                cboMaSeri.addItem(seri);
            }
            rmvListerBtnFilter();

            btnFilter.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String selected = cboMaSeri.getSelectedItem().toString();
                    dsCTHD = bus.filterMaSeri(maHD, selected);
                    changeTable();
                }
            });
        }
        SellTicketDao temp = new SellTicketDao();
        txtTongHD.setText(String.valueOf(temp.tinhTongHoaDon(maHD)));
    }
    private void initTable(){
        List<CTHD> dsct = bus.getDsCTHD(maHD);

        String[] columns = {"Mã hóa đơn", "Hệ số","Mã series","Tên sách","Tổng tiền"};
        dtm.setColumnIdentifiers(columns);

        for(CTHD ct:dsct){
            Object[] t = {ct.getMa_phieu(), ct.getHe_so(),ct.getMa_series(),ct.getTenSach(),ct.getTienSach()};
            dtm.addRow(t);
        }
        tblCTHD.setModel(dtm);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < tblCTHD.getColumnCount(); i++) {
            tblCTHD.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    }

    public JPanel getMain() {
        return main;
    }

    public void setMain(JPanel main) {
        this.main = main;
    }
}
