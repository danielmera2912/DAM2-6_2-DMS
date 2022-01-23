import java.util.*
import kotlin.random.Random.Default.nextInt
abstract class Dispara() {
    open fun dispara(){
        println("ha disparado.")
    }
}
open class Casa(nombre: String): Dispara(){
    var nombre=nombre
    override fun dispara(){
        println(this.nombre+" ha disparado por la puerta")
    }
}
open class Coche(nombre: String): Dispara(){
    var nombre=nombre
    override fun dispara(){
        println(this.nombre+" ha disparado por el tubo de escape")
    }
}
open class Bocadillo(nombre: String): Dispara(){
    var nombre=nombre
    override fun dispara(){
        println(this.nombre+" ha disparado?")
    }
}
open class ArmaDeFuego(): Dispara() {
    open protected var nombre: String= ""
    open protected var municion: Int= 0
    open protected var municionARestar: Int=0
    open protected var tipoDeMunicion:String= ""
    open protected var danio: Int= 0
    open protected var radio: String= ""

    constructor(nombre:String, tipoDeMunicion:String, danio:Int, municion:Int=100) : this() {
        this.nombre=nombre
        this.tipoDeMunicion=tipoDeMunicion
        this.danio=danio
        this.municion=municion
    }
    override fun dispara(){
        println("El arma "+this.nombre+" ha disparado. Queda tras el disparo : "+this.municion+" balas")
    }
    open fun recarga(){
        this.municion=this.municion+1
        println("Recarga la munición, queda: "+this.municion)
    }
}

open class Pistola() : ArmaDeFuego() {
    constructor(nombre:String, municion:Int=100) : this() {
        this.nombre=nombre
        this.tipoDeMunicion="pequeña"
        this.danio=2
        this.radio="pequeño"
        this.municion=municion
    }
    override var municionARestar=1
    override fun dispara() {
        this.municion=this.municion-this.municionARestar
        super.dispara()
    }
}
open class Rifle() : ArmaDeFuego() {
    constructor(nombre:String, municion:Int=100) : this() {
        this.nombre=nombre
        this.tipoDeMunicion="mediana"
        this.danio=10
        this.radio="pequeño"
        this.municion=municion
    }
    override var municionARestar=2
    override fun dispara() {
        this.municion=this.municion-this.municionARestar
        super.dispara()
    }
}
open class Bazooka() : ArmaDeFuego() {
    constructor(nombre:String, municion:Int=100) : this() {
        this.nombre=nombre
        this.tipoDeMunicion="grande"
        this.danio=35
        this.radio="amplío"
        this.municion=municion
    }
    override var municionARestar=3
    override fun dispara() {
        this.municion=this.municion-this.municionARestar
        super.dispara()
    }
}
fun main(args: Array<String>) {
    var armas: ArrayList<Dispara> = arrayListOf()
    var random = 0
    for(num in 0..5){
        random = (1..6).random()
        if(random==1){
            armas.add(Pistola("Pistola "+(num+1).toString()))
        }
        else if(random==2){
            armas.add(Rifle("Rifle "+(num+1).toString()))
        }
        else if(random==3){
            armas.add(Bazooka("Bazooka "+(num+1).toString()))
        }
        else if(random==4){
            armas.add(Casa("Casa "+(num+1)))
        }
        else if(random==5){
            armas.add(Coche("Coche "+(num+1)))
        }
        else{
            armas.add(Bocadillo("Bocadillo "+(num+1)))
        }
    }
    val mapaArmas= mutableMapOf<String,Dispara>("arma1" to armas.get(0), "arma2" to armas.get(1), "arma3" to armas.get(2), "arma4" to armas.get(3), "arma5" to armas.get(4), "arma6" to armas.get(5))
    mapaArmas.get("arma1")?.dispara()
    mapaArmas.get("arma2")?.dispara()
    mapaArmas.get("arma3")?.dispara()
    mapaArmas.get("arma4")?.dispara()
    mapaArmas.get("arma5")?.dispara()
    mapaArmas.get("arma6")?.dispara()

}
//b) ¿Que beneficios obtienes al usar una clase abstracta? ¿Y de una interface?
// Te permite crear una estructura definida junto con sus funciones o propiedades que serán constantemente
//reutilizadas y reescrita, la interfaz es bastante similar pero la interfaz se usa para dar un comportamiento
// repetido a ciertas clases, mientras que la clase abstracta es guardar la jerarquía de clases, la cual
// unas heredan ciertos comportamientos de otras, que tal vez estas hereden de otras
//c) ¿Que modificadores y mecanismos has utilizado para bloquear y forzar la herencia de clases y métodos?
// Para permitir que una clase sea heredada se usa open y para cerrar la herencia "final",
// en los métodos se utiliza "override" para expresar que una función que posee el padre será sobrescrita