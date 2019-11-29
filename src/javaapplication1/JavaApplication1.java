/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

import java.sql.*;

/**
 *
 * @author pedago
 */
public class JavaApplication1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            Class.forName("org.postgresql.Driver");
            System.out.println("pilote chargé");

            Connection conn = null;

            try {
                String url = "jdbc:postgresql://localhost:5432/postgres";
                String user = "postgres";
                String passwd = "postgres";
                conn = DriverManager.getConnection(url, user, passwd);
                if (conn != null) {
                    System.out.println("connection établie ?");
                    sommeCapacite(conn);
                    try {
                        conn.close();
                        System.out.println("ça y est, c'est fermé");
                    } catch (Exception e) {
                        System.err.println("t'as merdé quelque part, mais je sais pas ou");
                    }
                } else {
                    System.out.println("connection echoué ?");
                }
            } catch (SQLException ex) {
                System.err.println("t'as fait de la merde");
            }
        } catch (java.lang.ClassNotFoundException e) {
            System.err.println(e.getMessage());
        }
    }

    public static void afficheTableAvion(Connection con) throws SQLException {
        String requete = "select * from avion;";
        try {
            Statement stmt = null;
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(requete);
            if (rs != null) {
                while (rs.next()) {
                    System.out.println(
                            "\t Numéro : " + rs.getInt("avnum") + "\t");
                    System.out.println("\t Nom : " + rs.getString("avnom") + "\t ");
                    System.out.println("\t Capacité : " + rs.getInt("capacite") + "\t");
                    System.out.println("\t Localisation : " + rs.getString("localisation") + "\t");
                    System.out.println("\n---------------------------------");
                }
                System.out.println();
            } else {
                throw new SQLException("prout");
            }
            rs.close(); // fermeture de l'instance ResultSet
            stmt.close(); // fermeture de l'instance Statement
        } catch (SQLException e) {
            System.err.println("lol je sais pas !");
        }
    }

    public static void listePilote(Connection con) throws SQLException{
        String requete = "select * from pilote;";
        try {
            Statement stmt = null;
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(requete);
            if (rs != null) {
                while (rs.next()) {
                    System.out.println(
                            "\t Numéro : " + rs.getInt("plnum") + "\t");
                    System.out.println("\t Nom : " + rs.getString("plnom") + "\t ");
                    System.out.println("\t Prénom : " + rs.getString("plprenom") + "\t");
                    System.out.println("\t Ville : " + rs.getString("ville") + "\t");
                    System.out.println("\t date : " + rs.getDate("datenaiss").toString() + "\t");
                    System.out.println("\t date : " + rs.getFloat("salaire") + "\t");
                    System.out.println("\n---------------------------------");
                }
                System.out.println();
            } else {
                throw new SQLException("prout");
            }
            rs.close(); // fermeture de l'instance ResultSet
            stmt.close(); // fermeture de l'instance Statement
        } catch (SQLException e) {
            System.err.println("lol je sais pas !");
        }
    }
    
    public static void salaireMoyenPilotes(Connection con) throws SQLException{
        String requete = "select AVG(salaire) as moyenne from pilote;";
        try {
            Statement stmt = null;
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(requete);
            if (rs != null) {
                while (rs.next()) {
                    System.out.println("La moyenne des salaires est : " + rs.getFloat("moyenne"));
                }
                System.out.println();
            } else {
                throw new SQLException("prout");
            }
            rs.close(); // fermeture de l'instance ResultSet
            stmt.close(); // fermeture de l'instance Statement
        } catch (SQLException e) {
            System.err.println("lol je sais pas !");
        }
    }
    
    public static void sommeCapacite(Connection con) throws SQLException{
        String requete = "select sum(salaire) as somme from pilote;";
        try {
            Statement stmt = null;
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(requete);
            if (rs != null) {
                while (rs.next()) {
                    System.out.println("La capacité de tout les avion confondu est : " + rs.getFloat("somme"));
                }
                System.out.println();
            } else {
                throw new SQLException("prout");
            }
            rs.close(); // fermeture de l'instance ResultSet
            stmt.close(); // fermeture de l'instance Statement
        } catch (SQLException e) {
            System.err.println("lol je sais pas !");
        }
    }
    
    
    public static void majLocalisation(){
        
    }
}
