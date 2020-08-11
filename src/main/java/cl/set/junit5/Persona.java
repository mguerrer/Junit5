package cl.set.junit5;

public class Persona 
{
    String name, lastname;
    Persona(String nombre, String apellido)
    {
        name = nombre;
        lastname = apellido;
    }
    String nombre()
    {
        return name;
    }
    String apellido()
    {
        return lastname;
    }
}