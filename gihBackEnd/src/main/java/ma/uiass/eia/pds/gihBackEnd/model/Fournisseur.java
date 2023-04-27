package ma.uiass.eia.pds.gihBackEnd.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "TFournisseur")
public class Fournisseur {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "Id")
        private int id;

        @Column(name = "Nom")
        private String nom;

        @Column(name = "Email")
        private String email;

        @Column(name = "Adresse")
        private String adresse;

        @Column(name = "Tel")
        private String tel;

        @Override
        public String toString() {
                return this.nom;
        }

        @OneToMany(mappedBy = "fournisseur")
        private List<DetailLivraison> detailLivraison;


        public Fournisseur() {
        }

        public Fournisseur( String nom, String email, String adresse, String tel) {
                this.nom = nom;
                this.email = email;
                this.adresse = adresse;
                this.tel = tel;
        }

        public Fournisseur(String nom) {
                this.nom = nom;
        }

        public int getId() {
                return id;
        }

        public void setId(int id) {
                this.id = id;
        }

        public String getNom() {
                return nom;
        }

        public void setNom(String nom) {
                this.nom = nom;
        }

        public String getEmail() {
                return email;
        }

        public void setEmail(String email) {
                this.email = email;
        }

        public String getAdresse() {
                return adresse;
        }

        public void setAdresse(String adresse) {
                this.adresse = adresse;
        }

        public String getTel() {
                return tel;
        }

        public void setTel(String tel) {
                this.tel = tel;
        }
}
