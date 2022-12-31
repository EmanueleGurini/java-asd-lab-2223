/**
 * 
 */
package it.unicam.cs.asdl2223.es1;

/**
 * Un oggetto di questa classe permette di rappresentare una equazione di
 * secondo grado e di trovarne le soluzioni reali. I valori dei parametri
 * dell'equazione possono cambiare nel tempo. All'inizio e ogni volta che viene
 * cambiato un parametro la soluzione dell'equazione non esiste e deve essere
 * calcolata con il metodo <code>solve()</code>. E' possibile sapere se al
 * momento la soluzione dell'equazione esiste con il metodo
 * <code>isSolved()</code>. Qualora la soluzione corrente non esista e si tenti
 * di ottenerla verrà lanciata una eccezione.
 * 
 * @author Template: Luca Tesei, Implementation: Emanuele Gurini - emanuele.gurini@studenti.unicam.it
 *
 */
public class EquazioneSecondoGradoModificabileConRisolutore {
    /*
     * Costante piccola per il confronto di due numeri double
     */
    private static final double EPSILON = 1.0E-15;

    private double a;

    private double b;

    private double c;

    private boolean solved;

    private SoluzioneEquazioneSecondoGrado lastSolution;

    /**
     * Costruisce una equazione di secondo grado modificabile. All'inizio
     * l'equazione non è risolta.
     * 
     * 
     * @param a
     *              coefficiente del termine x^2, deve essere diverso da zero.
     * @param b
     *              coefficiente del termine x
     * @param c
     *              termine noto
     * @throws IllegalArgumentException
     *                                      se il parametro <code>a</code> è
     *                                      zero
     * 
     */
    public EquazioneSecondoGradoModificabileConRisolutore(double a, double b,
            double c) {

        if(a < this.EPSILON) {
            throw new IllegalArgumentException("Value 'a' should not be equals to 0");
        }

        this.a = a;
        this.b = b;
        this.c = c;
        this.solved = false; // At the beginning, no solution is available yet.
        this.lastSolution = null; //
    }

    /**
     * @return il valore corrente del parametro a
     */
    public double getA() {
        return a;
    }

    /**
     * Cambia il parametro a di questa equazione. Dopo questa operazione
     * l'equazione andrà risolta di nuovo.
     * 
     * @param a
     *              il nuovo valore del parametro a
     * @throws IllegalArgumentException
     *                                      se il nuovo valore è zero
     */
    public void setA(double a) {
        if(a < this.EPSILON) {
            throw new IllegalArgumentException("Value 'a' should not be equals to 0");
        }

        this.a = a;
        this.solved = false;
    }

    /**
     * @return il valore corrente del parametro b
     */
    public double getB() {
        return b;
    }

    /**
     * Cambia il parametro b di questa equazione. Dopo questa operazione
     * l'equazione andrà risolta di nuovo.
     * 
     * @param b
     *              il nuovo valore del parametro b
     */
    public void setB(double b) {
        this.b = b;
        this.solved = false;
    }

    /**
     * @return il valore corrente del parametro c
     */
    public double getC() {
        return this.c;
    }

    /**
     * Cambia il parametro c di questa equazione. Dopo questa operazione
     * l'equazione andrà risolta di nuovo.
     * 
     * @param c
     *              il nuovo valore del parametro c
     */
    public void setC(double c) {
        this.c = c;
        this.solved = false;
    }

    /**
     * Determina se l'equazione, nel suo stato corrente, è già stata risolta.
     * 
     * @return true se l'equazione è risolta, false altrimenti
     */
    public boolean isSolved() {
        return solved;
    }

    /**
     * Risolve l'equazione risultante dai parametri a, b e c correnti. Se
     * l'equazione era già stata risolta con i parametri correnti non viene
     * risolta di nuovo.
     */
    public void solve() {
        if (isSolved()) {
            return;
        }

        double DELTA = Math.pow(this.b, 2) - 4 * (this.a * this.c);


      if(DELTA < this.EPSILON) {

           this.lastSolution = new SoluzioneEquazioneSecondoGrado(
                   new EquazioneSecondoGrado(this.a, this.b, this.c));

      } else if (Math.abs(DELTA) < this.EPSILON) {
          this.lastSolution = new SoluzioneEquazioneSecondoGrado(
                  new EquazioneSecondoGrado(this.a, this.b, this.c), -this.b / 2 * this.a);

      } else {
          this.lastSolution = new SoluzioneEquazioneSecondoGrado(
                  new EquazioneSecondoGrado(this.a, this.b, this.c),
                  -this.b + DELTA / 2 * this.a,
                  -this.b - DELTA / 2 * this.a
          );
      }



    }

    /**
     * Restituisce la soluzione dell'equazione risultante dai parametri
     * correnti. L'equazione con i parametri correnti deve essere stata
     * precedentemente risolta.
     *
     * @return la soluzione
     * @throws IllegalStateException
     *                                   se l'equazione risulta non risolta,
     *                                   all'inizio o dopo il cambiamento di
     *                                   almeno uno dei parametri
     */
    public SoluzioneEquazioneSecondoGrado getSolution() {
        if(!this.solved) {
            throw new IllegalStateException("The solution you're trying to retrieve does not exist.");
        }
        return this.lastSolution;
    }

}
