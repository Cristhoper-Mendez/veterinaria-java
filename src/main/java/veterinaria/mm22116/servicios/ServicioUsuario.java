package veterinaria.mm22116.servicios;

import java.util.List;
import java.util.ArrayList;
import veterinaria.mm22116.entidades.Usuario;
import veterinaria.mm22116.interfaces.IUsuarioServicio;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ServicioUsuario implements IUsuarioServicio {

    String archivo;

    public ServicioUsuario(String archivo) {
        this.archivo = archivo;
    }

    @Override
    public List<Usuario> ObtenerUsuarios() {
        List<Usuario> LstUsuarios = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(this.archivo));

            String linea;

            if ((linea = reader.readLine()) != null && !linea.equals("")) {
                Usuario usuario = new Usuario(linea);

                LstUsuarios.add(usuario);
            }

        } catch (IOException e) {
        }

        return LstUsuarios;
    }

    @Override
    public boolean CrearUsuario(Usuario usuario) {
        try {
            List<Usuario> LstUsuario = this.ObtenerUsuarios();
            int size = LstUsuario.size();
            int id = 1;

            if (size > 0) {
                id = LstUsuario.get(size - 1).getIdUsuario() + 1;
            }

            usuario.setIdUsuario(id);
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(this.archivo))) {
                writer.write(usuario.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean ActualizarUsuario(Usuario usuario) {
        try {
            List<Usuario> LstUsuarios = this.ObtenerUsuarios();

            for (int i = 0; i < LstUsuarios.size(); i++) {
                if (LstUsuarios.get(i).getIdUsuario() == usuario.getIdUsuario()) {
                    LstUsuarios.set(i, usuario);
                    break;
                }
            }

            GuardarUsuarios(LstUsuarios);
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    @Override
    public boolean EliminarUsuario(int usuarioId) {
        try {
            List<Usuario> LstUsuarios = this.ObtenerUsuarios();

            for (int i = 0; i < LstUsuarios.size(); i++) {
                if (LstUsuarios.get(i).getIdUsuario() == usuarioId) {
                    Usuario usuario = LstUsuarios.get(i);
                    usuario.setActivo(false);

                    LstUsuarios.set(i, usuario);
                    break;
                }
            }

            GuardarUsuarios(LstUsuarios);
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    private void GuardarUsuarios(List<Usuario> LstUsuarios) {
        try {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(this.archivo, false))) {
                for (Usuario usuario : LstUsuarios) {
                    writer.write(usuario.toString());
                    writer.newLine();
                }
            }
        } catch (IOException e) {
        }
    }

    @Override
    public List<Usuario> ObtenerUsuariosActivos() {
        List<Usuario> LstUsuarios = this.ObtenerUsuarios();
        List<Usuario> lstUsuariosActivos = new ArrayList<>();

        for (Usuario usuario : LstUsuarios) {
            if (usuario.getActivo()) {
                lstUsuariosActivos.add(usuario);
            }
        }

        return lstUsuariosActivos;

    }

    @Override
    public boolean ValidarUsuario(String email, String password) {

        boolean result = false;

        Usuario usuario = this.GetUsuarioPorCorreo(email);

        if (usuario != null && usuario.getPassword().equals(password)) {
            result = true;
        }

        return result;
    }

    @Override
    public Usuario GetUsuarioPorCorreo(String email) {
        List<Usuario> LstUsuarios = this.ObtenerUsuariosActivos();
        Usuario usuario = null;

        for (Usuario u : LstUsuarios) {
            if (u.getCorreo().equals(email)) {
                usuario = u;
            }
        }

        return usuario;
    }
}
