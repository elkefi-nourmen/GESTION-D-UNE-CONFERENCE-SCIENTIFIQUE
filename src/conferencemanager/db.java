/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conferencemanager;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
/**
 *
 * @author nourm
 */
public class db {
  
    
    public static Connection connect(){
    Connection con = null;
    try{
    Class.forName("com.mysql.jdbc.Driver");
   con = DriverManager.getConnection("jdbc:mysql://localhost/gestionconf", "root", "");
System.out.println("connecté au BD");    }catch(Exception e){    
System.out.println("pas connecté au BD");}
    return con;
    }
    
    public static boolean islogin(String mail,String pass){
        Connection con=connect();
        try{
        PreparedStatement ps=con.prepareStatement("select * from utilisateur where email='"+mail+"'and password='"+pass+"'");
        ResultSet rs=ps.executeQuery();
        if (rs.next())
            return true;
       
        }catch(SQLException e){System.out.println(e.getMessage());}
        return false;
        }
       utilisateur a=new utilisateur();
    public static boolean insert(utilisateur a) throws SQLException{
            Connection con=connect();
PreparedStatement ps = con.prepareStatement("SELECT * FROM utilisateur WHERE email=? ");
ps.setString(1, a.getEmail());

ResultSet rs=ps.executeQuery();
        if(rs.next())
                    return false;
        else 
            
         ps=con.prepareStatement("INSERT INTO utilisateur (nom, prenom, email, password, type) VALUES ('"+a.getNom()+"','"+a.getPrenom()+"','"+a.getEmail()+"','"+a.getPassword()+"','"+a.getType()+"')");
        int executeUpdate = ps.executeUpdate();
         
   return true;
    }
     public static utilisateur infos (String mail,String pass) throws SQLException{
        Connection con=connect();
        PreparedStatement ps=con.prepareStatement("select * from utilisateur where email='"+mail+"'and password='"+pass+"'");
        ResultSet rs=ps.executeQuery();
        utilisateur u=new utilisateur();
        if (rs.next()) {
        u = new utilisateur();
        u.setId_utilisateur(rs.getInt("id_utilisateur"));
        u.setEmail(rs.getString("email"));
        u.setPassword(rs.getString("password"));
        u.setType(rs.getString("type"));
        u.setNom(rs.getString("nom"));
        u.setPrenom(rs.getString("prenom"));
    }
        return u;

     }
     public static ObservableList<conference> afficher_conf () throws SQLException{
      Connection con=connect();
      ObservableList<conference>list=FXCollections.observableArrayList();
        PreparedStatement ps=con.prepareStatement("select id_conference,titre,institution,lieu,duree,frais_inscrit,themes,date_soumis_article,date_limite_inscrit from conference");
        ResultSet rs=ps.executeQuery();
        while(rs.next())
        {
        list.add(new conference(rs.getInt("id_conference"),rs.getString("titre"),rs.getString("institution"),rs.getString("lieu"),rs.getString("duree"),rs.getFloat("frais_inscrit"),rs.getString("themes"),rs.getDate("date_soumis_article"),rs.getDate("date_limite_inscrit")));
        }
        return list;
     }
     public static boolean ajoutconf(conference conf) throws SQLException{
     
      Connection con=connect();
PreparedStatement ps=con.prepareStatement("SELECT * FROM conference where titre='"+conf.getTitre()+"'");
        ResultSet rs=ps.executeQuery();
        if(rs.next())
                    return false;
        else {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
String datesoum = sdf.format(conf.getDate_soumission());
String dateinscrit = sdf.format(conf.getDate_inscription());

ps=con.prepareStatement("INSERT INTO conference(institution,president,comite_sci,comite_org, duree, lieu, titre, themes, date_soumis_article, date_limite_inscrit, frais_inscrit) VALUES ('"+conf.getInstitution()+"','"+conf.getPresident()+"','"+conf.getComite_sci()+"','"+conf.getComite_org()+"','"+conf.getDuree()+"','"+conf.getLieu()+"','"+conf.getTitre()+"','"+conf.getThemes()+"','"+datesoum+"','"+dateinscrit+"','"+conf.getFrais()+"')");
        int executeUpdate = ps.executeUpdate();
         
   return true;}
     }
     
     
  public static ObservableList<comite> afficher_coms () throws SQLException{
      Connection con=connect();
      ObservableList<comite>list=FXCollections.observableArrayList();
        PreparedStatement ps=con.prepareStatement("select id_comite,nom from comite where type='sci'");
        ResultSet rs=ps.executeQuery();
        while(rs.next())
        {
        list.add(new comite(rs.getInt("id_comite"),rs.getString("nom"),"sci"));
        }
        return list;
     }
   public static ObservableList<comite> afficher_como () throws SQLException{
      Connection con=connect();
      ObservableList<comite>list=FXCollections.observableArrayList();
        PreparedStatement ps=con.prepareStatement("select id_comite,nom from comite where type='org'");
        ResultSet rs=ps.executeQuery();
        while(rs.next())
        {
        list.add(new comite(rs.getInt("id_comite"),rs.getString("nom"),"org"));
        }
        return list;
     }
    public static boolean deleteconf(int i) throws SQLException {
    Connection con = connect();
    PreparedStatement ps = con.prepareStatement("DELETE FROM conference WHERE id_conference ='"+i+"'");
    int rowsAffected = ps.executeUpdate();
    return rowsAffected > 0;
}
    
     public static boolean updateconf(conference c) throws SQLException {
    Connection con = connect();
    PreparedStatement ps = con.prepareStatement("UPDATE conference SET titre=?, institution=?, lieu=?, duree=?, frais_inscrit=?, themes=?, date_soumis_article=?, date_limite_inscrit=?, comite_sci=?, comite_org=? WHERE id_conference=?");

    ps.setString(1, c.getTitre());
    ps.setString(2, c.getInstitution());
    ps.setString(3, c.getLieu());
    ps.setString(4, c.getDuree());
    ps.setFloat(5, c.getFrais());
    ps.setString(6, c.getThemes());
    ps.setDate(7, new java.sql.Date(c.getDate_soumission().getTime()));
    ps.setDate(8, new java.sql.Date(c.getDate_inscription().getTime()));
    ps.setInt(9, c.getComite_sci());
    ps.setInt(10, c.getComite_org());
    ps.setInt(11, c.getId_conference());

    int rowsUpdated = ps.executeUpdate();
    return rowsUpdated > 0;
}

     
      public static conference selectconf(int id) throws SQLException{
     conference c=new conference();
      Connection con=connect();
PreparedStatement ps=con.prepareStatement("SELECT * FROM conference where id_conference='"+id+"'");
        ResultSet rs=ps.executeQuery();

 while(rs.next())
        {
         c.setTitre(rs.getString("titre"));
        c.setThemes(rs.getString("themes"));
        c.setDuree(rs.getString("duree"));
        c.setFrais(rs.getFloat("frais_inscrit"));
        c.setDate_inscription(rs.getDate("date_limite_inscrit"));
        c.setDate_soumission(rs.getDate("date_soumis_article"));
        c.setInstitution(rs.getString("institution"));
        c.setLieu(rs.getString("lieu"));
         c.setComite_sci(rs.getInt("comite_sci"));
          c.setComite_org(rs.getInt("comite_org"));

        }
 return c;
      }
      
      
       public static ObservableList<keynotespeaker> afficher_notespeakers(int id) throws SQLException{
      Connection con=connect();
      ObservableList<keynotespeaker>list=FXCollections.observableArrayList();
        PreparedStatement ps=con.prepareStatement("select * from keynotespeaker where id_conference='"+id+"'");
        ResultSet rs=ps.executeQuery();
        while(rs.next())
        {
        list.add(new keynotespeaker(rs.getInt("id_keynotespeaker"),rs.getString("nom"),rs.getString("institution"),rs.getString("pays_origine"),rs.getString("titre_presentation"),rs.getInt("id_conference")));
        }
        return list;
     }
      
      
      
       public static boolean deletekeynote(int i) throws SQLException {
    Connection con = connect();
    PreparedStatement ps = con.prepareStatement("DELETE FROM keynotespeaker WHERE id_keynotespeaker ='"+i+"'");
    int rowsAffected = ps.executeUpdate();
    return rowsAffected > 0;
}
      
    public static boolean insert_keynotespeaker(keynotespeaker k) throws SQLException{
    
     
      Connection con=connect();
PreparedStatement ps=con.prepareStatement("SELECT * FROM keynotespeaker where nom='"+k.getNom()+"'");
        ResultSet rs=ps.executeQuery();
        if(rs.next())
                    return false;
        else {
          ps=con.prepareStatement("INSERT INTO keynotespeaker(nom,institution,pays_origine,titre_presentation,id_conference) VALUES ('"+k.getNom()+"','"+k.getInstitution()+"','"+k.getPays_origine()+"','"+k.getTitre_presentation()+"','"+k.getId_conference()+"')");
        int executeUpdate = ps.executeUpdate();
        return true;
    }  
      
      
    }           
      
      public static boolean update_keynotespeaker(keynotespeaker k) throws SQLException{
       Connection con = connect();
    PreparedStatement ps = null;
        try {
            ps = con.prepareStatement("UPDATE keynotespeaker set nom='"+k.getNom()+"',institution='"+k.getInstitution()+"',titre_presentation='"+k.getTitre_presentation()+"',pays_origine='"+k.getPays_origine()+"' WHERE id_keynotespeaker ='"+k.getId_keynotespeaker()+"'");
        } catch (SQLException ex) {
            Logger.getLogger(db.class.getName()).log(Level.SEVERE, null, ex);
        }
      
            int rowsAffected = ps.executeUpdate();
      return rowsAffected>0;
      }

//pour afficher tous les articles 
   public static ObservableList<article> select_tous_art(int id) throws SQLException {
    Connection con = connect();
    ObservableList<article> list = FXCollections.observableArrayList();
    PreparedStatement ps = null;
    ResultSet rs = null;
    try {
        ps = con.prepareStatement("SELECT * FROM article WHERE id_conference ='"+id+"'");
       
        rs = ps.executeQuery();
        while (rs.next()) {
           
       list.add(new article(rs.getInt("id_article"),rs.getString("titre"),rs.getBytes("fichier"),rs.getString("status"),rs.getInt("membre_evaluation"),rs.getInt("id_conference")));         
        }
}catch (SQLException ex) {
        Logger.getLogger(db.class.getName()).log(Level.SEVERE, null, ex);
}        return list;
}    
   
   
   
  
//afficher les membres de types membre com sci     
    public static ObservableList<memberinfo> getMembersInfo(int idComite) throws SQLException {
    Connection con = connect();
    ObservableList<memberinfo> list = FXCollections.observableArrayList();
    
    PreparedStatement ps = null;
    ResultSet rs = null;
    try {
        ps = con.prepareStatement("SELECT id_membre_comite_sci, id_utilisateur FROM membre_comite_sci WHERE id_comite=?");
        ps.setInt(1, idComite);
        rs = ps.executeQuery();
        while (rs.next()) {
            int idMembreComite = rs.getInt("id_membre_comite_sci");
            int idUtilisateur = rs.getInt("id_utilisateur");

            PreparedStatement ps2 = con.prepareStatement("SELECT nom FROM utilisateur WHERE id_utilisateur=?");
            ps2.setInt(1, idUtilisateur);
            ResultSet rs2 = ps2.executeQuery();
            if (rs2.next()) {
                String name = rs2.getString("nom");
                list.add(new memberinfo(idMembreComite, name));
            }
            rs2.close();
            ps2.close();
        }
    } catch (SQLException ex) {
        Logger.getLogger(db.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
        if (rs != null) {
            rs.close();
        }
        if (ps != null) {
            ps.close();
        }
        con.close();
    }
    return list;
}

  public static boolean affecter_art(article a,memberinfo m) throws SQLException{
  
   Connection con = connect();
    PreparedStatement ps = null;
        try {
            ps = con.prepareStatement("UPDATE article SET status='UE', membre_evaluation=? where id_article=?");
        } catch (SQLException ex) {
            Logger.getLogger(db.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            ps.setInt(1, m.getId_membrecomite());
        } catch (SQLException ex) {
            Logger.getLogger(db.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            ps.setInt(2, a.getId_article());
        } catch (SQLException ex) {
            Logger.getLogger(db.class.getName()).log(Level.SEVERE, null, ex);
        }
   
  int rowsAffected = ps.executeUpdate();
  return rowsAffected>0;
  
  }  
  // inscription d'un participant a une conference
   public static boolean inscription(conference c,String s) throws SQLException{
  int rowsAffected;
  
   Connection con = connect();
    PreparedStatement ps = null;
        try {
            ps = con.prepareStatement("select * from participants where id_utilisateur='"+c.getPresident()+"'and id_conference='"+c.getId_conference()+"'");
        } catch (SQLException ex) {
            Logger.getLogger(db.class.getName()).log(Level.SEVERE, null, ex);
        }
        ResultSet rs = ps.executeQuery();
        if (rs.next())
        {return false;}
        else 
        {
                        ps = con.prepareStatement("Insert into participants set id_utilisateur='"+utilisateur.user.getId_utilisateur()+"',id_conference='"+c.getId_conference()+"',mode_paiement='"+s+"'");
                 rowsAffected = ps.executeUpdate();
                

        }

      return (rowsAffected>0);
  
  }  
  
   public static void main(String []args){
    connect();
    } 
        

}
