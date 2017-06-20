package com.example.kerkouche.myapplication.backend.Service;

import com.example.kerkouche.myapplication.backend.Model.Annonce;
import com.example.kerkouche.myapplication.backend.Model.Annonce_s;
import com.example.kerkouche.myapplication.backend.Model.Commentaire;
import com.example.kerkouche.myapplication.backend.Model.Disponibilite;
import com.example.kerkouche.myapplication.backend.Model.RendezVous;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kerkouche on 30/04/17.
 */

public class DataBaseService {
    public static final String className = "com.mysql.jdbc.Driver";
    public static final String chaine = "jdbc:mysql://localhost:3306/Projet2";
    public static final String username = "root";
    public static final String password = "13121994";

    public Connection connecter(){
        Connection con = null;
        try {
            Class.forName(className);
            con = DriverManager.getConnection(chaine,username,password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }

    //----------------------------------------------------------------------------------------------
    //connexion d'un user
    public Integer connexion(String username,String password){
        String query = "SELECT Id_user FROM User where (Username=? and Password=?) or (Email=? and Password=?)";
        Connection conn = null;
        PreparedStatement pst = null;
        conn = connecter();
        int i=0;

        try {
            pst=conn.prepareStatement(query);
            pst.setString(1,username);
            pst.setString(2,password);
            pst.setString(3,username);
            pst.setString(4,password);
            ResultSet rs =pst.executeQuery();
            if (rs.first()) {
                i = rs.getInt("Id_user");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if(conn!=null) try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(pst!=null) try {
            pst.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }
    //----------------------------------------------------------------------------------------------
    //ajout de commentaire
    public boolean addCommentaire(Commentaire commentaire){

        String query = "insert into Commentaire(Id_annonce_comm" +
               ",Id_user_comm,Text,Note,Date_comm,Time_comm) values(?,?,?,?,?,?)";
        Connection connection = connecter();
        PreparedStatement prst = null;
        int i = -1;
        try {
            prst = connection.prepareStatement(query);
            prst.setInt(1, Integer.parseInt(commentaire.getId_annonce_comm()));
            prst.setInt(2, Integer.parseInt(commentaire.getId_user_comm()));
            prst.setString(3,commentaire.getText());
            prst.setInt(4, Integer.parseInt(commentaire.getNote()));
            prst.setDate(5, java.sql.Date.valueOf(commentaire.getDate_comm()));
            prst.setTime(6,java.sql.Time.valueOf(commentaire.getTime_comm()));

            i=prst.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (prst!=null){
            try {
                prst.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (connection!=null){
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return (i !=-1);
    }
    //----------------------------------------------------------------------------------------------
    public List<Disponibilite> getDisponibiliteList(int id_annonce){
        List<Disponibilite> disponibiliteList = new ArrayList<Disponibilite>();
        String query = "SELECT Id_user_annonce from Annonce where  	Id_annonce = ?";
        Connection conn = null;
        PreparedStatement pst = null;
        PreparedStatement pst2 = null;
        conn = connecter();

        try {
            pst=conn.prepareStatement(query);
            pst.setInt(1,id_annonce);
            ResultSet rs =pst.executeQuery();
            if (rs.first()) {
                int id_user = rs.getInt("Id_user_annonce");

                String query2 = "SELECT * from Disponibilite where Id_user_dispo=?";
                pst2=conn.prepareStatement(query2);
                pst2.setInt(1,id_user);
                ResultSet rs2 =pst2.executeQuery();
                while (rs2.next()){
                    Disponibilite disponibilite = new Disponibilite();
                    disponibilite.setDatedispoDebut(rs2.getString("DateDebut"));
                    disponibilite.setDatedispofin(rs2.getString("DateFIn"));
                    disponibiliteList.add(disponibilite);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if(conn!=null) try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(pst!=null) try {
            pst.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return disponibiliteList;
    }
    //----------------------------------------------------------------------------------------------
    public List<Commentaire> getCommentaireList(int id_annonce){
        List<Commentaire> commentaireList = new ArrayList<Commentaire>();
        String query = "SELECT * from Commentaire where Id_annonce_comm = ?";
        Connection conn = null;
        PreparedStatement pst = null;
        PreparedStatement pst2 = null;
        conn = connecter();

        try {
            pst=conn.prepareStatement(query);
            pst.setInt(1,id_annonce);
            ResultSet rs =pst.executeQuery();
            while (rs.next()){
                int id_user = rs.getInt("Id_user_comm");

                String query2 = "SELECT Nom,Prenom from User where Id_user=?";
                pst2=conn.prepareStatement(query2);
                pst2.setInt(1,id_user);
                ResultSet rs2 =pst2.executeQuery();
                if (rs2.first()) {
                    Commentaire commentaire = new Commentaire();

                    commentaire.setNom(rs2.getString("Nom"));
                    commentaire.setPrenom(rs2.getString("Prenom"));

                    commentaire.setText(rs.getString("Text"));
                    commentaire.setNote(rs.getString("Note"));
                    commentaire.setDate_comm(rs.getString("Date_comm"));
                    commentaire.setTime_comm(rs.getString("Time_comm"));
                    commentaireList.add(commentaire);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if(conn!=null) try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(pst!=null) try {
            pst.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return commentaireList;
    }
    //----------------------------------------------------------------------------------------------
    // ajout d'un RDV
    public boolean addRDV(RendezVous rendezVous){
        String query = "insert into RDV(Id_annonce_rdv" +
                ",Id_user_rdv,Id_proprio_rdv,Lieu,Date_rdv,Heure_rdv,Valider) " +
                " values(?,?,?,?,?,?,?)";
        Connection connection = connecter();
        PreparedStatement prst = null;
        int i = -1;
        try {
            prst = connection.prepareStatement(query);
            prst.setInt(1, Integer.parseInt(rendezVous.getId_annonce_rdv()));
            prst.setInt(2, Integer.parseInt(rendezVous.getId_user_rdv()));
            prst.setInt(3,Integer.parseInt(rendezVous.getId_proprio_rdv()));
            prst.setString(4,rendezVous.getLieu());
            prst.setDate(5,rendezVous.getDate_rdv());
            prst.setTime(6,rendezVous.getHeure_rdv());
            prst.setString(7,rendezVous.getValider());

            i=prst.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (prst!=null){
            try {
                prst.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (connection!=null){
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return (i !=-1);
    }

    //----------------------------------------------------------------------------------------------
    public List<Annonce_s> getMainAnnaonces(String density ,String in  ){
        List<Annonce_s> list = new ArrayList<Annonce_s>();
        Connection conn = null;
        PreparedStatement pst = null;String type="main";
        String query = "select * from V_projet_1 where  Type_image='"+type+"' and Densite='"+density+"' limit "+in ;
        try {
            conn = connecter();
            /*pst=conn.prepareStatement(query);
            pst.setString(1,"main");
            pst.setString(2,density);
            ResultSet rs =pst.executeQuery();*/
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                Annonce_s annonce = new Annonce_s();
                annonce.setId(Integer.parseInt(rs.getString("Id_annonce")));
                annonce.setTitre(rs.getString("Titre"));
                annonce.setPrix(Integer.parseInt(rs.getString("Prix")));
                annonce.setUrl(rs.getString("Url"));
                list.add(annonce);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(conn!=null) try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(pst!=null) try {
            pst.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  list;
    }
    //----------------------------------------------------------------------------------------------
    public List<Annonce_s> Recherche (String density ,String region,String tlogm  ){
        List<Annonce_s> list = new ArrayList<Annonce_s>();
        Connection conn = null; String query = null;int b=0;
        PreparedStatement pst = null;String type="main";
        if (!region.equals("null")  && !tlogm.equals("null") ) {b=1;query = "select * from V_projet_1 where  Type_image=? and Densite=? and Region = ? and Typelogm = ?" ;}
        if (region.equals("null")  && !tlogm.equals("null") ){b=2;query = "select * from V_projet_1 where  Type_image=? and Densite=?  and Typelogm = ?";}
        if (!region.equals("null")  && tlogm.equals("null") ) {b=3;query = "select * from V_projet_1 where  Type_image=? and Densite=? and Region = ?  " ;}
        if (region.equals("null")  && tlogm.equals("null") ) {b=4;query = "select * from V_projet_1 where  Type_image=? and Densite=?  " ;}

        try {
            conn = connecter();
            pst=conn.prepareStatement(query);
            pst.setString(1,"main");
            pst.setString(2,density);
            if (b==1 ) {pst.setString(3,region);pst.setString(4,tlogm);}
            if (b==3 ) pst.setString(3,region);
            if (b==2 ) pst.setString(3,tlogm);
            ResultSet rs =pst.executeQuery();
            // Statement statement = conn.createStatement();
            //  ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                Annonce_s annonce = new Annonce_s();
                annonce.setId(Integer.parseInt(rs.getString("Id_annonce")));
                annonce.setTitre(rs.getString("Titre"));
                annonce.setPrix(Integer.parseInt(rs.getString("Prix")));
                annonce.setUrl(rs.getString("Url"));
                list.add(annonce);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(conn!=null) try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(pst!=null) try {
            pst.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  list;
    }
    //----------------------------------------------------------------------------------------------
    public List<Annonce> getDetailAnnaonces(String density, int id_annonce){
        List<Annonce> list = new ArrayList<Annonce>();
        Connection conn = null;
        PreparedStatement pst = null;
        String query = "select * from V_projet_1 where Id_annonce=?  and Densite=?";
        try {
            conn = connecter();
            pst=conn.prepareStatement(query);
            pst.setInt(1,id_annonce);
            pst.setString(2,density);
            ResultSet rs =pst.executeQuery();
            Annonce annonce = new Annonce();
            List<String> listUrls = new ArrayList<String>();
            rs.next();
            annonce.setUtilisateur(rs.getString("Id_user_annonce"));
            annonce.setDescription(rs.getString("Description"));
            annonce.setRegion(rs.getString("Region"));
            annonce.setNbrChambres(rs.getInt("NbrChambres"));
            annonce.setSurface(rs.getInt("Surface"));
            annonce.setTypelogm(rs.getString("Typelogm"));
            annonce.setLatitude(rs.getDouble("Latitude"));
            annonce.setLongitude(rs.getDouble("Longitude"));
            listUrls.add(rs.getString("Url"));
            while (rs.next()) {
                listUrls.add(rs.getString("Url"));
            }
            annonce.setListeImageDetai(listUrls.toArray(new String[listUrls.size()]));
            list.add(annonce);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(conn!=null) try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(pst!=null) try {
            pst.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return  list;
    }
    //----------------------------------------------------------------------------------------------
    public List<RendezVous> getRDVList(int id_proprio){
        List<RendezVous> RDVList = new ArrayList<RendezVous>();
        String query = "SELECT * from RDV where Id_proprio_rdv = ?";
        Connection conn = null;
        PreparedStatement pst = null;
        PreparedStatement pst2 = null;
        PreparedStatement pst3 = null;
        conn = connecter();

        try {
            pst=conn.prepareStatement(query);
            pst.setInt(1,id_proprio);
            ResultSet rs =pst.executeQuery();
            while (rs.next()){
                int Id_annonce_rdv = rs.getInt("Id_annonce_rdv");
                int Id_user_rdv = rs.getInt("Id_user_rdv");

                String query2 = "SELECT  Titre from Annonce where Id_annonce=?";
                pst2=conn.prepareStatement(query2);
                pst2.setInt(1,Id_annonce_rdv);
                ResultSet rs2 =pst2.executeQuery();
                if (rs2.first()) {

                    String query3 = "SELECT  Nom,Prenom from User where Id_user=?";
                    pst3=conn.prepareStatement(query3);
                    pst3.setInt(1,Id_user_rdv);
                    ResultSet rs3 =pst3.executeQuery();
                    if (rs3.first()) {
                        RendezVous rendezVous = new RendezVous();

                        rendezVous.setId_rdv(rs.getString("Id_rdv"));
                        rendezVous.setAnnonce_titre(rs2.getString("Titre"));
                        rendezVous.setNom_user_rdv(rs3.getString("Nom"));
                        rendezVous.setPrenom_user_rdv(rs3.getString("Prenom"));
                        rendezVous.setDate2_rdv(String.valueOf(rs.getDate("Date_rdv")));
                        rendezVous.setTime_rdv(String.valueOf(rs.getTime("Heure_rdv")));
                        rendezVous.setLieu(rs.getString("Lieu"));
                        rendezVous.setValider(rs.getString("Valider"));
                        RDVList.add(rendezVous);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if(conn!=null) try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(pst!=null) try {
            pst.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return RDVList;
    }
    //----------------------------------------------------------------------------------------------
    public boolean updateRDV(int id_rdv,String valider){
        String query = "update RDV set Valider=? where Id_rdv=?";
        Connection connection = connecter();
        PreparedStatement prst = null;
        int i = -1;
        try {
            prst = connection.prepareStatement(query);

            prst.setString(1, valider);
            prst.setInt(2, id_rdv);

            i=prst.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (prst!=null){
            try {
                prst.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (connection!=null){
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return (i !=-1);
    }
    //----------------------------------------------------------------------------------------------
    public List<RendezVous> getRDVNotification(int Id_rdv,int id_proprio){
        List<RendezVous> RDVList = new ArrayList<RendezVous>();
        String query = "SELECT * from RDV where Id_rdv > ? and Id_proprio_rdv = ?";
        Connection conn = null;
        PreparedStatement pst = null;
        PreparedStatement pst2 = null;
        PreparedStatement pst3 = null;
        conn = connecter();

        try {
            pst=conn.prepareStatement(query);
            pst.setInt(1,Id_rdv);
            pst.setInt(2,id_proprio);
            ResultSet rs =pst.executeQuery();
            while (rs.next()){
                int Id_annonce_rdv = rs.getInt("Id_annonce_rdv");
                int Id_user_rdv = rs.getInt("Id_user_rdv");

                String query2 = "SELECT  Titre from Annonce where Id_annonce=?";
                pst2=conn.prepareStatement(query2);
                pst2.setInt(1,Id_annonce_rdv);
                ResultSet rs2 =pst2.executeQuery();
                if (rs2.first()) {

                    String query3 = "SELECT  Nom,Prenom from User where Id_user=?";
                    pst3=conn.prepareStatement(query3);
                    pst3.setInt(1,Id_user_rdv);
                    ResultSet rs3 =pst3.executeQuery();
                    if (rs3.first()) {
                        RendezVous rendezVous = new RendezVous();

                        rendezVous.setId_rdv(rs.getString("Id_rdv"));
                        rendezVous.setAnnonce_titre(rs2.getString("Titre"));
                        rendezVous.setNom_user_rdv(rs3.getString("Nom"));
                        rendezVous.setPrenom_user_rdv(rs3.getString("Prenom"));
                        rendezVous.setDate2_rdv(String.valueOf(rs.getDate("Date_rdv")));
                        rendezVous.setTime_rdv(String.valueOf(rs.getTime("Heure_rdv")));
                        rendezVous.setLieu(rs.getString("Lieu"));
                        rendezVous.setValider(rs.getString("Valider"));
                        RDVList.add(rendezVous);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if(conn!=null) try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(pst!=null) try {
            pst.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return RDVList;
    }
    //----------------------------------------------------------------------------------------------
}
