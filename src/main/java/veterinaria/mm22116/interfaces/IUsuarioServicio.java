package veterinaria.mm22116.interfaces;

import veterinaria.mm22116.entidades.Usuario;
import java.util.List;

public interface IUsuarioServicio {

    public List<Usuario> ObtenerUsuarios();

    public List<Usuario> ObtenerUsuariosActivos();

    public boolean CrearUsuario(Usuario usuario);

    public boolean ActualizarUsuario(Usuario usuario);

    public boolean EliminarUsuario(int idUsuario);
    
    public boolean ValidarUsuario(String email, String password);
    
    public Usuario GetUsuarioPorCorreo(String correo);

}
