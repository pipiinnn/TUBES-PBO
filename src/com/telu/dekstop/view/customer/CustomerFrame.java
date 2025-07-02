    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.telu.dekstop.view.customer;

import com.stripbandunk.jwidget.JDynamicTable;
import com.stripbandunk.jwidget.model.DynamicTableModel;
import com.telu.dekstop.controller.CartController;
import com.telu.dekstop.controller.ItemController;
import com.telu.dekstop.controller.OrderController;
import com.telu.dekstop.model.Cart;
import com.telu.dekstop.model.Item;
import com.telu.dekstop.model.Order;
import com.telu.dekstop.model.OrderDetail;
import com.telu.dekstop.model.OrderStatus;
import com.telu.dekstop.model.PaymentMethod;
import com.telu.dekstop.util.Database;
import com.telu.dekstop.view.customer.dialog.CheckoutDialog;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author maula
 */
public class CustomerFrame extends javax.swing.JFrame {

    // 
    private DynamicTableModel<Item> tableModelKopi;
    private DynamicTableModel<Item> tableModelNonKopi;
    private DynamicTableModel<Cart> tableModelCart;
    private final ItemController itemController = new ItemController();
    private final CartController cartController = new CartController();
    private final OrderController orderController = new OrderController();

    public CustomerFrame() {
        initComponents();
        setExtendedState(MAXIMIZED_BOTH);

        setupTables();
        clearCartAndReload(false);

    }

    private void setupTables() {
        tableModelKopi = initItemTable(TableKopi);
        tableModelNonKopi = initItemTable(TableNonKopi);

        tableModelCart = new DynamicTableModel<>(Cart.class);
        TableKeranjang.setDynamicModel(tableModelCart);
        TableKeranjang.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    private DynamicTableModel<Item> initItemTable(JDynamicTable table) {
        DynamicTableModel<Item> model = new DynamicTableModel<>(Item.class);
        table.setDynamicModel(model);
        table.removeColumn(table.getColumnModel().getColumn(0));
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        return model;
    }

    private void loadTableKopi() {
        List<Item> items = itemController.getByCategory(1);
        tableModelKopi.clear();
        for (Item item : items) {
            tableModelKopi.add(item);
        }
    }

    private void loadTableNonKopi() {
        List<Item> items = itemController.getByCategory(2);
        tableModelNonKopi.clear();
        for (Item item : items) {
            tableModelNonKopi.add(item);
        }
    }

    private void loadKeranjang() {
        List<Cart> result = cartController.getAllItems();
        tableModelCart.clear();
        for (Cart cart : result) {
            tableModelCart.add(cart);
        }

        int total = cartController.getTotalPrice();
        jLabel4.setText("Total Harga: Rp" + total);
    }

    private List<Cart> getItemFromCartTable() {
        return new ArrayList<>(tableModelCart.getData());
    }

    private Item getSelectedItemFromTables() {
        // Ambil data yang dipilih user
        int selectedRowNonKopi = TableNonKopi.getSelectedRow();
        int selectedRowKopi = TableKopi.getSelectedRow();

        // Cek jika dua-duanya dipilih
        if (selectedRowKopi != -1 && selectedRowNonKopi != -1) {
            JOptionPane.showMessageDialog(this, "Pilih dari satu tabel saja! Tidak boleh memilih dari dua tabel sekaligus.");
            TableKopi.clearSelection();
            TableNonKopi.clearSelection();
            return null;
        }

        if (selectedRowKopi != -1) {
            return tableModelKopi.get(TableKopi.convertRowIndexToModel(selectedRowKopi));
        }

        if (selectedRowNonKopi != -1) {
            return tableModelNonKopi.get(TableNonKopi.convertRowIndexToModel(selectedRowNonKopi));
        }

        return null;

    }

    private void clearCartAndReload(boolean isCheckout) {

        if (cartController.clearCart(isCheckout)) {
            loadKeranjang();
            loadTableKopi();
            loadTableNonKopi();
        } else {
            JOptionPane.showMessageDialog(this, "Gagal membersihkan keranjang!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
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

        jSplitPane1 = new javax.swing.JSplitPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TableKopi = new com.stripbandunk.jwidget.JDynamicTable();
        jPanel9 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        TableNonKopi = new com.stripbandunk.jwidget.JDynamicTable();
        jPanel2 = new javax.swing.JPanel();
        btnCart = new javax.swing.JButton();
        btnCancelItem = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        TableKeranjang = new com.stripbandunk.jwidget.JDynamicTable();
        jPanel5 = new javax.swing.JPanel();
        btnDeleteFromCart = new javax.swing.JButton();
        btnEditCart = new javax.swing.JButton();
        btnCheckout = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        btnLogOut = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel8.setLayout(new java.awt.BorderLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("Menu Kopi");
        jLabel1.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanel8.add(jLabel1, java.awt.BorderLayout.NORTH);

        jScrollPane1.setViewportView(TableKopi);

        jPanel8.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanel1.add(jPanel8, java.awt.BorderLayout.NORTH);

        jPanel9.setLayout(new java.awt.BorderLayout());

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setText("Menu Non Kopi");
        jLabel2.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanel9.add(jLabel2, java.awt.BorderLayout.NORTH);

        jScrollPane3.setViewportView(TableNonKopi);

        jPanel9.add(jScrollPane3, java.awt.BorderLayout.CENTER);

        jPanel1.add(jPanel9, java.awt.BorderLayout.CENTER);

        btnCart.setText("Tambah ke Keranjang");
        btnCart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCartActionPerformed(evt);
            }
        });
        jPanel2.add(btnCart);

        btnCancelItem.setText("Batalkan Pilihan");
        btnCancelItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelItemActionPerformed(evt);
            }
        });
        jPanel2.add(btnCancelItem);

        jPanel1.add(jPanel2, java.awt.BorderLayout.SOUTH);

        jSplitPane1.setLeftComponent(jPanel1);

        jPanel3.setLayout(new java.awt.BorderLayout());

        jPanel4.setLayout(new java.awt.BorderLayout());

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setText("Keranjang");
        jPanel4.add(jLabel3, java.awt.BorderLayout.CENTER);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("Total: Rp");
        jPanel4.add(jLabel4, java.awt.BorderLayout.EAST);

        jPanel3.add(jPanel4, java.awt.BorderLayout.NORTH);

        jScrollPane2.setViewportView(TableKeranjang);

        jPanel3.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        btnDeleteFromCart.setText("Hapus dari Keranjang");
        btnDeleteFromCart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteFromCartActionPerformed(evt);
            }
        });
        jPanel5.add(btnDeleteFromCart);

        btnEditCart.setText("Edit");
        btnEditCart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditCartActionPerformed(evt);
            }
        });
        jPanel5.add(btnEditCart);

        btnCheckout.setText("Checkout");
        btnCheckout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCheckoutActionPerformed(evt);
            }
        });
        jPanel5.add(btnCheckout);

        jPanel3.add(jPanel5, java.awt.BorderLayout.SOUTH);

        jSplitPane1.setRightComponent(jPanel3);

        getContentPane().add(jSplitPane1, java.awt.BorderLayout.CENTER);

        jPanel6.setLayout(new java.awt.BorderLayout());

        btnLogOut.setText("LogOut");
        btnLogOut.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        btnLogOut.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnLogOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogOutActionPerformed(evt);
            }
        });
        jPanel7.add(btnLogOut);

        jPanel6.add(jPanel7, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel6, java.awt.BorderLayout.SOUTH);

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLogOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogOutActionPerformed
        // TODO add your handling code here:
        clearCartAndReload(false);
        this.dispose();
    }//GEN-LAST:event_btnLogOutActionPerformed

    private void btnCartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCartActionPerformed
        // TODO add your handling code here:

        Item selectedItem = getSelectedItemFromTables();

        if (selectedItem == null) {
            JOptionPane.showMessageDialog(this, "Pilih data terlebih dahulu!");
            return;
        }

        // Menampilkan inputan user memilih berapa barang
        String input = JOptionPane.showInputDialog(this, "Masukkan Jumlah barang");

        if (input == null || input.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Jumlah tidak boleh kosong!");
            return;
        }

        if (!isNumeric(input)) {
            JOptionPane.showMessageDialog(this, "Input harus berupa angka!");
            return;
        }

        int quantity = Integer.parseInt(input);
        if (quantity < 1) {
            JOptionPane.showMessageDialog(this, "Jumlah harus lebih besar dari 0");
            return;
        }

        if (quantity > selectedItem.getStock()) {
            JOptionPane.showMessageDialog(this, "Stok untuk " + selectedItem.getName() + " tidak mencukupi!\nStok saat ini: " + selectedItem.getStock());
            return;
        }

        // Hitung sub total
        int subtotal = (int) (selectedItem.getPrice() * quantity);

        // kurangi stok
        itemController.updateStock(selectedItem.getId(), quantity);

        // Simpan ke table cart
        Cart result = new Cart();
        result.setItem_id(selectedItem);
        result.setQuantity(quantity);
        result.setSubTotal(subtotal);

        boolean success = cartController.addToCart(result);
        if (success) {
            JOptionPane.showMessageDialog(this, "Item berhasil ditambahkan ke keranjang!");

            loadKeranjang();
            loadTableKopi();
            loadTableNonKopi();
        } else {
            JOptionPane.showMessageDialog(this, "Gagal menambahkan item ke keranjang!");
        }
    }//GEN-LAST:event_btnCartActionPerformed

    private void btnDeleteFromCartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteFromCartActionPerformed
        // TODO add your handling code here:
        if (TableKeranjang.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(this, "Silahkan pilih data terlebih dahulu!");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this, "Apakah anda yakin ingin menghapus item?", "Hapus Data", JOptionPane.OK_CANCEL_OPTION);
        if (confirm != JOptionPane.OK_OPTION) {
            return;
        }

        Cart parameter = tableModelCart.get(TableKeranjang.convertRowIndexToModel(TableKeranjang.getSelectedRow()));

        boolean success = cartController.deleteCart(parameter);
        if (success) {
            JOptionPane.showMessageDialog(this, "Data berhasil dihapus!");
            loadKeranjang();
            loadTableKopi();
            loadTableNonKopi();
        } else {
            JOptionPane.showMessageDialog(this, "Gagal menghapus item dari keranjnag!");
        }
    }//GEN-LAST:event_btnDeleteFromCartActionPerformed

    private void btnEditCartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditCartActionPerformed
        // TODO add your handling code here:
        if (TableKeranjang.getSelectedRow() != -1) {
            
            Cart selectedCart = tableModelCart.get(TableKeranjang.convertRowIndexToModel(TableKeranjang.getSelectedRow()));
            String input = JOptionPane.showInputDialog("Masukkan jumlah barang");

            if (input == null || input.trim().isEmpty()) {
                return;
            }

            try {
                Integer updateStock = Integer.valueOf(input);

                if (updateStock == 0) {
                    JOptionPane.showMessageDialog(this, "Pilih jumlah stock terlebih dahulu");
                } else if (updateStock > selectedCart.getItem_id().getStock()) {
                    JOptionPane.showMessageDialog(this, "Jumlah melebihi stock!");
                } else {

                    CartController controller = new CartController();
                    boolean success = controller.updateCart(updateStock, selectedCart);
                    if (success) {
                        JOptionPane.showMessageDialog(this, "Data berhasil diubah");
                    } else {
                        JOptionPane.showMessageDialog(this, "Stok tidak mencukupi");
                    }
                    
                    loadKeranjang();
                    loadTableKopi();
                    loadTableNonKopi();

                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Input harus berupa angka");
            }

        } else {
            JOptionPane.showMessageDialog(this, "Pilih data terlebih dahulu!");
        }
    }//GEN-LAST:event_btnEditCartActionPerformed

    private void btnCheckoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCheckoutActionPerformed
        // TODO add your handling code here:

        // Menangkap data pesanan dari Table Keranjang
        
        List<Cart> cartItems = getItemFromCartTable();
        
        if(cartItems.isEmpty()){
            JOptionPane.showMessageDialog(this, "Keranjang masih kosong!");
            return;
        }
        
        int totalQuantity = 0;
        int totalItems = 0;
        float totalPrice = 0;

        for (Cart cart : cartItems) {
            totalQuantity += cart.getQuantity();
            totalPrice += cart.getSubTotal();
        }

        totalItems = cartItems.size();

        CheckoutDialog dialog = new CheckoutDialog();
        dialog.setCartItem(cartItems);
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);

        if (!dialog.isConfirmed()) {
            return;
        }

        // Menangkap data pilihan pembayaran
        String paymentMethod = dialog.getSelectedPaymentMethod();

        // Konversi String ke PaymentMethod enum
        PaymentMethod method = "QRIS".equalsIgnoreCase(paymentMethod) ? PaymentMethod.QRIS : PaymentMethod.BAYAR_DI_KASIR;
        // Set status sebagai enum
        OrderStatus orderStatus = method == PaymentMethod.QRIS ? OrderStatus.Lunas : OrderStatus.Belum_Lunas;
        
        // Inisialisai Table Order
        Order order = new Order();
        order.setPayment_method(method);
        order.setStatus(orderStatus);
        order.setTotalQuantity(totalQuantity);
        order.setTotalItems(totalItems);
        order.setTotalPrice(totalPrice);
        order.setOrderDate(new Date());

        List<OrderDetail> details = new ArrayList<>();
        for (Cart cart : cartItems) {
            OrderDetail detail = new OrderDetail();
            detail.setOrderId(cart.getId());
            detail.setItemId(cart.getItem_id());
            detail.setQuantity(cart.getQuantity());
            detail.setSubtotal((float) cart.getSubTotal());
            details.add(detail);
        }

        order.setDetails(details);

        int orderId = orderController.createOrder(order);

        if (orderId > 0) {
            JOptionPane.showMessageDialog(this, "Pesanan berhasil disimpan!");
            clearCartAndReload(true);

            // Tampilkan struk
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("order_id", orderId);

            try {
                JasperDesign jasperDesign = JRXmlLoader.load("src/resources/report/StrukCustomer.jrxml");
                JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
                JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, Database.getConnection());

                JasperViewer viewer = new JasperViewer(jasperPrint, false);
                viewer.setTitle("Struk Pesanan #" + orderId);
                viewer.setVisible(true);

            } catch (JRException ex) {
                Logger.getLogger(CustomerFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Gagal menyimpan pesanan.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnCheckoutActionPerformed

    private void btnCancelItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelItemActionPerformed
        // TODO add your handling code here:

        TableKopi.clearSelection();
        TableNonKopi.clearSelection();
    }//GEN-LAST:event_btnCancelItemActionPerformed

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.stripbandunk.jwidget.JDynamicTable TableKeranjang;
    private com.stripbandunk.jwidget.JDynamicTable TableKopi;
    private com.stripbandunk.jwidget.JDynamicTable TableNonKopi;
    private javax.swing.JButton btnCancelItem;
    private javax.swing.JButton btnCart;
    private javax.swing.JButton btnCheckout;
    private javax.swing.JButton btnDeleteFromCart;
    private javax.swing.JButton btnEditCart;
    private javax.swing.JButton btnLogOut;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSplitPane jSplitPane1;
    // End of variables declaration//GEN-END:variables
}
