package org.meridor.fias.beans;

public class FiscalCodes {
    
    private final String postalCode;
    
    private final String ifnsPersonsCode;
    
    private final String ifnsCompaniesCode;
    
    private final String okato;
    
    private final String oktmo;

    public FiscalCodes(String postalCode, String ifnsPersonsCode, String ifnsCompaniesCode, String okato, String oktmo) {
        this.postalCode = postalCode;
        this.ifnsPersonsCode = ifnsPersonsCode;
        this.ifnsCompaniesCode = ifnsCompaniesCode;
        this.okato = okato;
        this.oktmo = oktmo;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getIfnsPersonsCode() {
        return ifnsPersonsCode;
    }

    public String getIfnsCompaniesCode() {
        return ifnsCompaniesCode;
    }

    public String getOkato() {
        return okato;
    }

    public String getOktmo() {
        return oktmo;
    }
}
