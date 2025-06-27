package ModeloDAO;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import ModeloVO.UsuarioVO;
import Util.HibernateUtil;

public class UsuarioDAO {
	
	public static List<UsuarioVO> findAllUsuarios() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "FROM usuario";
            Query<UsuarioVO> query = session.createQuery(hql);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static UsuarioVO getUsuario(String email, String clave) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "FROM usuario WHERE email = :email AND clave = :clave";
            Query<UsuarioVO> query = session.createQuery(hql);
            query.setParameter("email", email);
            query.setParameter("clave", clave);
            return query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static UsuarioVO getUsuarioEntero(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(UsuarioVO.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static List<UsuarioVO> getUsuariosPorRol(int rol_id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "FROM usuario WHERE rol_id = :rol_id";
            Query<UsuarioVO> query = session.createQuery(hql);
            query.setParameter("rol_id", rol_id);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void updateUsuario(UsuarioVO usuario) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(usuario);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public static void updateUsuarioClave(int id, String clave) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            UsuarioVO usuario = session.get(UsuarioVO.class, id);
            usuario.setClave(clave);
            session.update(usuario);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public static void insertarUsuario(UsuarioVO usuario) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(usuario);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}
