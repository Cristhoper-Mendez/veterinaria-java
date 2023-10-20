package veterinaria.mm22116.servicios;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import veterinaria.mm22116.interfaces.ITipoPacienteServicio;
import veterinaria.mm22116.entidades.TipoPaciente;
import java.util.List;
import java.util.ArrayList;

public class ServicioTipoPaciente implements ITipoPacienteServicio {

    String archivo;

    public ServicioTipoPaciente(String archivo) {
        this.archivo = archivo;
    }

    @Override
    public List<TipoPaciente> ObtenerTipoPacientesActivos() {
        List<TipoPaciente> LstTipoPaciente = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(this.archivo));

            String linea;

            while ((linea = reader.readLine()) != null && !linea.equals("")) {
                TipoPaciente tipoPaciente = new TipoPaciente(linea);

                if (tipoPaciente.getActivo()) {
                    LstTipoPaciente.add(tipoPaciente);
                }
            }
        } catch (IOException e) {
        }

        return LstTipoPaciente;
    }

    @Override
    public List<TipoPaciente> ObtenerTipoPacientes() {
        List<TipoPaciente> LstTipoPaciente = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(this.archivo));

            String linea;

            while ((linea = reader.readLine()) != null && !linea.equals("")) {
                TipoPaciente tipoPaciente = new TipoPaciente(linea);
                LstTipoPaciente.add(tipoPaciente);
            }
        } catch (IOException e) {
        }

        return LstTipoPaciente;
    }

    @Override
    public boolean CrearTipoPaciente(TipoPaciente tipoPaciente) {
        try {
            List<TipoPaciente> LstTipo = this.ObtenerTipoPacientes();
            int size = LstTipo.size();
            int id = 1;

            if (size > 0) {
                id = LstTipo.get(size - 1).getIdTipoPaciente() + 1;
            }

            tipoPaciente.setIdTipoPaciente(id);
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(this.archivo))) {
                String str = tipoPaciente.toString();
                writer.write(str);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }

        return true;
    }

    @Override
    public boolean ActualizarTipoPaciente(TipoPaciente tipoPaciente) {
        try {
            List<TipoPaciente> LstTipoPaciente = this.ObtenerTipoPacientes();

            for (int i = 0; i < LstTipoPaciente.size(); i++) {
                if (LstTipoPaciente.get(i).getIdTipoPaciente() == tipoPaciente.getIdTipoPaciente()) {
                    LstTipoPaciente.set(i, tipoPaciente);
                    break;
                }
            }

            GuardarTiposPacientes(LstTipoPaciente);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean EliminarTipoPaciente(int tipoPacienteId) {
        try {
            List<TipoPaciente> LstTipoPaciente = this.ObtenerTipoPacientes();

            for (int i = 0; i < LstTipoPaciente.size(); i++) {
                if (LstTipoPaciente.get(i).getIdTipoPaciente() == tipoPacienteId) {
                    TipoPaciente tipoPaciente = LstTipoPaciente.get(i);
                    tipoPaciente.setActivo(false);
                    LstTipoPaciente.set(i, tipoPaciente);
                    break;
                }
            }

            GuardarTiposPacientes(LstTipoPaciente);

        } catch (Exception e) {
            return false;
        }
        return true;
    }

    private void GuardarTiposPacientes(List<TipoPaciente> LstTipoPacientes) {
        try {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(this.archivo, false))) {
                for (TipoPaciente tipoPaciente : LstTipoPacientes) {
                    writer.write(tipoPaciente.toString());
                    writer.newLine();
                }
            }
        } catch (IOException e) {
        }
    }

    @Override
    public TipoPaciente ObtenerTipoPacientePorId(int idTipoPaciente) {
        TipoPaciente tipoPaciente = null;
        List<TipoPaciente> lst = this.ObtenerTipoPacientesActivos();

        for (TipoPaciente tp : lst) {
            if (tp.getIdTipoPaciente() == idTipoPaciente) {
                tipoPaciente = tp;
            }
        }

        return tipoPaciente;
    }
}
