package com.example;

public abstract class ItemBiblioteca {
    private String titulo;
    private String autor;

    public ItemBiblioteca(String titulo, String autor) {
        this.titulo = titulo;
        this.autor = autor;
    }

    public abstract void emprestar();

    public class Livro extends ItemBiblioteca implements Reservavel {
        public Livro(String titulo, String autor) {
            super(titulo, autor);
        }
    
        @Override
        public void emprestar() {
            System.out.println("Livro emprestado por 14 dias.");
        }
    
        @Override
        public void reservar() {
            System.out.println("Livro reservado.");
        }
    }
    
    public class Revista extends ItemBiblioteca {
        public Revista(String titulo, String autor) {
            super(titulo, autor);
        }
    
        @Override
        public void emprestar() {
            System.out.println("Revista emprestada por 7 dias.");
        }
    }
    
    public class MaterialEletronico extends ItemBiblioteca {
        public MaterialEletronico(String titulo, String autor) {
            super(titulo, autor);
        }
    
        @Override
        public void emprestar() {
            System.out.println("Material eletrônico emprestado por 7 dias.");
        }
    }
    
}
