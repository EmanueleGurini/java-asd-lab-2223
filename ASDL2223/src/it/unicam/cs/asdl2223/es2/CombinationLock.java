package it.unicam.cs.asdl2223.es2;

/**
 * Un oggetto cassaforte con combinazione ha una manopola che può essere
 * impostata su certe posizioni contrassegnate da lettere maiuscole. La
 * serratura si apre solo se le ultime tre lettere impostate sono uguali alla
 * combinazione segreta.
 *
 * @author Luca Tesei (template)
 *         emanuele.gurini@studenti.unicam.it (implementazione)
 */
public class CombinationLock {
    private String aCombination;
    private boolean isOpen;

    private char[] combination;

    /**
     * Costruisce una cassaforte <b>aperta</b> con una data combinazione
     * 
     * @param aCombination
     *                         la combinazione che deve essere una stringa di 3
     *                         lettere maiuscole dell'alfabeto inglese
     * @throw IllegalArgumentException se la combinazione fornita non è una
     *        stringa di 3 lettere maiuscole dell'alfabeto inglese
     * @throw NullPointerException se la combinazione fornita è nulla
     */
    public CombinationLock(String aCombination) {

        if(aCombination.isEmpty()) {
            throw new NullPointerException("Please, check your combination: shouldn't be NULL.");
        }

        if(!isCombinationInsertedValid(aCombination)) {
            throw new IllegalArgumentException("Please, insert the right combination: should be three characters longs, and each letter should be UPPERCASE.");
        }

        this.aCombination = aCombination;
        this.isOpen = true;
        this.combination = new char[3];
    }

    /**
     * Imposta la manopola su una certaposizione.
     * 
     * @param aPosition
     *                      un carattere lettera maiuscola su cui viene
     *                      impostata la manopola
     * @throws IllegalArgumentException
     *                                      se il carattere fornito non è una
     *                                      lettera maiuscola dell'alfabeto
     *                                      inglese
     */
    public void setPosition(char aPosition) {

        if (!Character.isUpperCase(aPosition)) throw new IllegalArgumentException("Please, input should be uppercase.");

        this.combination[0] = this.combination[1];
        this.combination[1] = this.combination[2];
        this.combination[2] = aPosition;
    }

    /**
     * Tenta di aprire la serratura considerando come combinazione fornita le
     * ultime tre posizioni impostate. Se l'apertura non va a buon fine le
     * lettere impostate precedentemente non devono essere considerate per i
     * prossimi tentativi di apertura.
     */
    public void open() {

        String combinationToString = String.valueOf(this.combination);

        if(this.aCombination.equals(combinationToString)) {
            this.isOpen = true;
        } else {
            this.combination[this.combination.length-1] = 0;
        }
    }

    /**
     * Determina se la cassaforte è aperta.
     * 
     * @return true se la cassaforte è attualmente aperta, false altrimenti
     */
    public boolean isOpen() {
        return this.isOpen;
    }

    /**
     * Chiude la cassaforte senza modificare la combinazione attuale. Fa in modo
     * che se si prova a riaprire subito senza impostare nessuna nuova posizione
     * della manopola la cassaforte non si apre. Si noti che se la cassaforte
     * era stata aperta con la combinazione giusta le ultime posizioni impostate
     * sono proprio la combinazione attuale.
     */
    public void lock() {
        this.isOpen = false;
        this.combination[this.combination.length-1] = 0;
    }

    /**
     * Chiude la cassaforte e modifica la combinazione. Funziona solo se la
     * cassaforte è attualmente aperta. Se la cassaforte è attualmente chiusa
     * rimane chiusa e la combinazione non viene cambiata, ma in questo caso le
     * le lettere impostate precedentemente non devono essere considerate per i
     * prossimi tentativi di apertura.
     * 
     * @param aCombination
     *                         la nuova combinazione che deve essere una stringa
     *                         di 3 lettere maiuscole dell'alfabeto inglese
     * @throw IllegalArgumentException se la combinazione fornita non è una
     *        stringa di 3 lettere maiuscole dell'alfabeto inglese
     * @throw NullPointerException se la combinazione fornita è nulla
     */
    public void lockAndChangeCombination(String aCombination) {

        if(!isOpen) {
            this.combination[this.combination.length-1] = 0;
        }

        if(isOpen) {
            if(aCombination.isEmpty()) {
                throw new NullPointerException("Please, check your combination: shouldn't be NULL.");
            }

            if(!isCombinationInsertedValid(aCombination)) {
                throw new IllegalArgumentException("Please, insert the right combination: should be three characters longs, and each letter should be UPPERCASE.");
            }

            this.aCombination = aCombination;
            this.lock();
        }

    }

    public boolean isCombinationInsertedValid(String aCombination) {
       return aCombination.matches("^[A-Z]{3}$");
    }
}