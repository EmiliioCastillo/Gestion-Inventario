package com.projectjava.demosclient.paginator;

import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

public class PageRender <T>{
    private String url;
    private Page<T> page;
    private int numTotalPaginas;
    private int numElemPaginas;
    private int pagActual;

    private List<PageItem> paginas;

    public PageRender(String url, Page<T> page) {
        this.url = url;
        this.page = page;
        this.paginas = new ArrayList<PageItem>();

        numElemPaginas = page.getSize();
        numTotalPaginas = page.getTotalPages();
        pagActual = page.getNumber() + 1;

        //Logica para manejar los rangos de la paginacion
        int desde, hasta;
        //Si el total de paginas es menor al numero de elementos que hay dentro de la pagina
        //entonces mostrara desde la primera hasta la ultima pagina donde haya elementos que mostrar
        if(numTotalPaginas <= numElemPaginas){
            desde = 1;
            hasta = numTotalPaginas;
            //Sino si en la pagina actual es menor al numdelementos por pagina va a ir desde la 1 hasta la última
        }else {
            if(pagActual < numElemPaginas / 2){
                desde = 1;
                hasta = numElemPaginas;
                //Sino si en la pagina actual es mayor a 20 ( num elementos por pagina 10/2 = 5 y numtotalpag = 25, 25 - 5 = 20
                //va a mostar desde la pagina 16 hasta la final
            } else if(pagActual > numTotalPaginas - numElemPaginas / 2){
                desde = numTotalPaginas - numElemPaginas + 1;
                hasta = numTotalPaginas;
            }else{
            desde = pagActual - numTotalPaginas / 2;
                hasta = numElemPaginas;
            }
        }
        for (int i = 0; i < hasta; i++){
            paginas.add(new PageItem(desde + i, pagActual == desde + i));
        }
    }


    public String getUrl() {
        return url;
    }



    public int getNumTotalPaginas() {
        return numTotalPaginas;
    }


    public List<PageItem> getPaginas() {
        return paginas;
    }



    public int getPagActual() {
        return pagActual;
    }


    public boolean isHasNext(){
        return page.hasNext();
    }


    public boolean isHasPrevious(){
        return page.hasPrevious();
    }

    public boolean isFirst(){
        return page.isFirst();
    }

    public boolean isLast(){
        return page.isLast();
    }
}
