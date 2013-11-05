package farmacia;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

public class Farmacia {

    static ArrayList inventario = new ArrayList();
    static ArrayList recetas = new ArrayList();
    static ArrayList clientes = new ArrayList();
    static ArrayList medicamentos = new ArrayList();
    static ArrayList medicamentos2 = new ArrayList();
    static Clientes cs = new Clientes();
    static JLabel idReceta;
    static JLabel idCliente;
    static JTextField idC;
    static JTextField idR;
    static Farmacia f;
    static JLabel resultado;
    static JButton busca;

    public void mostrarMenu() {

    }

    public String Busca(String s, String t, ArrayList b, ArrayList inve) {
        int idNum = Integer.parseInt(s);
        int recetaNum = Integer.parseInt(t);
        Clientes cs = new Clientes(b);
        Cliente c = cs.buscarClientes(idNum);
        Inventario i = new Inventario(inve);

        if (c != null) {
            HistorialRecetas hr = new HistorialRecetas(c.getHistorialReceta());
            Receta r = hr.buscarReceta(recetaNum);

            if (r.puedeSurtirse(inve) == true) {
                r.setFechaUltimoResurtido("Hoy");
                resultado.setText("Se puede Surtir");
                return "Se puede surtir";
            } else {
                resultado.setText("no se puede surtir, no hay suficientes unidades en inventario");
                return "no se puede surtir, no hay suficientes unidades en inventario";
            }

        }
        resultado.setText("Cliente no existe");
        return "Cliente no existe";
    }

    public static void main(String[] args) {
        f = new Farmacia();
        Cliente c = new Cliente(1);
        Medicamento m = new Medicamento("Micodin", 1);
        Medicamento m2 = new Medicamento("naproxeno", 1);
        Medicamento m3 = new Medicamento("Paracetamol", 1);

        inventario.add(m);
        inventario.add(m2);

        medicamentos.add(m);
        medicamentos.add(m2);
        medicamentos.add(m3);

        medicamentos2.add(m);
        medicamentos2.add(m2);

        Receta r1 = new Receta(1, medicamentos);
        Receta r2 = new Receta(2, medicamentos2);

        recetas.add(r1);
        recetas.add(r2);

        clientes.add(c);
        cs = new Clientes(clientes);
        cs.setClientes(clientes);
        c.setHistorialReceta(recetas);

        //INTERFAZ
        JFrame window = new JFrame("Farmacia");
        window.setSize(400, 400);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel main = new JPanel();
        main.setLayout(new FlowLayout());

        idCliente = new JLabel("ID cliente");
        idCliente.setPreferredSize(new Dimension(60, 40));
        idC = new JTextField();
        idC.setPreferredSize(new Dimension(300, 40));

        idReceta = new JLabel("ID Receta");
        idReceta.setPreferredSize(new Dimension(60, 40));
        idR = new JTextField();
        idR.setPreferredSize(new Dimension(300, 40));

        resultado = new JLabel("");

        busca = new JButton("BUSCAR");
        busca.setPreferredSize(new Dimension(300, 40));
        busca.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                f.Busca(idC.getText().toString(), idR.getText().toString(), cs.getClientes(), inventario);
            }

        });
        main.add(idCliente);
        main.add(idC);
        main.add(idReceta);
        main.add(idR);
        main.add(busca);
        main.add(resultado);
        window.add(main);
        window.setVisible(true);

        //TERMINA INTERFAZ
        System.out.println(f.Busca("1", "1", cs.getClientes(), inventario));
        System.out.println(f.Busca("1", "2", cs.getClientes(), inventario));
        System.out.println(f.Busca("2", "2", cs.getClientes(), inventario));

    }

}
