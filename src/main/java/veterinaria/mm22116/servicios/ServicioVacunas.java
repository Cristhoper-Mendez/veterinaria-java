package veterinaria.mm22116.servicios;

import java.util.List;
import java.util.ArrayList;
import veterinaria.mm22116.entidades.Vacunas;
import veterinaria.mm22116.interfaces.IVacunasServicio;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ServicioVacunas implements IVacunasServicio {

    String archivo;

    public ServicioVacunas(String archivo) {
        this.archivo = archivo;
    }

    @Override
    public List<Vacunas> ObtenerVacunas() {
        List<Vacunas> LstVacunas = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(this.archivo));

            String linea;

            while ((linea = reader.readLine()) != null && !linea.equals("")) {
                Vacunas vacunas = new Vacunas(linea);

                LstVacunas.add(vacunas);
            }
        } catch (IOException e) {
        }

        return LstVacunas;
    }

    @Override
    public boolean CrearVacunas(Vacunas vacunas) {
        try {
            List<Vacunas> LstVacunas = this.ObtenerVacunas();
            int size = LstVacunas.size();
            int id = 1;

            if (size > 0) {
                id = LstVacunas.get(size - 1).getIdVacuna() + 1;
            }

            vacunas.setIdVacuna(id);
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(this.archivo))) {
                writer.write(vacunas.toString());
                writer.newLine();
            }

        } catch (IOException e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean ActualizarVacunas(Vacunas vacunas) {
        try {
            List<Vacunas> LstVacunas = this.ObtenerVacunas();

            for (int i = 0; i < LstVacunas.size(); i++) {
                if (LstVacunas.get(i).getIdVacuna() == vacunas.getIdVacuna()) {
                    LstVacunas.set(i, vacunas);
                    break;
                }
            }

            GuardarVacunas(LstVacunas);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean EliminarVacunas(int vacunaId) {
        try {
            List<Vacunas> LstVacunas = this.ObtenerVacunas();

            for (int i = 0; i < LstVacunas.size(); i++) {
                if (LstVacunas.get(i).getIdVacuna() == vacunaId) {
                    Vacunas vacunas = LstVacunas.get(i);
                    vacunas.setActivo(false);
                    LstVacunas.set(i, vacunas);
                    break;
                }
            }

            GuardarVacunas(LstVacunas);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    private void GuardarVacunas(List<Vacunas> LstVacunas) {
        try {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(this.archivo, false))) {
                for (Vacunas vacuna : LstVacunas) {
                    writer.write(vacuna.toString());
                    writer.newLine();
                }
            }
        } catch (IOException e) {
        }
    }

    @Override
    public List<Vacunas> ObtenerVacunasActivas() {
        List<Vacunas> LstVacunasActivas = new ArrayList<>();
        List<Vacunas> LstVacunas = this.ObtenerVacunas();

        for (Vacunas v : LstVacunas) {
            if (v.isActivo() == true) {
                LstVacunasActivas.add(v);
            }
        }

        return LstVacunasActivas;
    }

    @Override
    public List<Vacunas> ObtenerVacunasPorIdPaciente(int idPaciente) {
        List<Vacunas> LstVacunasPaciente = new ArrayList<>();
        List<Vacunas> LstVacunas = this.ObtenerVacunasActivas();

        for (Vacunas v : LstVacunas) {
            if (v.getIdPaciente() == idPaciente) {
                LstVacunasPaciente.add(v);
            }
        }

        return LstVacunasPaciente;
    }
}
