package com.example.kur;


class Tabl {
    static Cell[][] tabl; //таблица(двумерный массив) ячеек (поле для игры)
    private Cell[] t;

    //region Стандартные
    Tabl(){ //конструктор
        tabl = new Cell[10][10];
        t = new Cell[10];
    }

    void set_tabl(int[][] a){ //заполнение таблицы
        for(int i=1; i<10; i++) {
            for (int j=1; j<10; j++) {
                tabl[i][j]=new Cell(i, j);
                tabl[i][j].first_set(a[i][j]);
            }
        }
    }

    int[][] get_tabl(int[][] a){ //выдает таблицу
        for(int i=1; i<10; i++) {
            for (int j=1; j<10; j++) {
                a[i][j]=tabl[i][j].get_truevar();
            }
        }
        return a;
    }
    //endregion



    //region Работа со строчками

    private Metods[] tab_to_lines(Metods[] lines){ //создает массив строк из ячеек строк таблицы
        for(int i=1; i<10; i++){
            for(int j=1; j<10; j++){
                t[j]=new Cell();
                t[j]=tabl[i][j];
            }
            lines[i]=new Metods(t);
        }
        return lines;
    }

    private void lines_easy_check(){ //простая проверка для всех строк таблицы
        Metods[] lines = new Metods[10];
        lines = tab_to_lines(lines);
        lines = cells_easy_check(lines);
        lines_to_tab(lines);
    }

    private void lines_single_check(){ //проверка единственного варианта для всех строк таблицы
        Metods[] lines = new Metods[10];
        lines = tab_to_lines(lines);
        lines = cells_single_check(lines);
        lines_to_tab(lines);
    }

    private void lines_open_two_check(){ //метод открытой пары для всех строк таблицы
        Metods[] lines = new Metods[10];
        lines = tab_to_lines(lines);
        lines = cells_open_two_check(lines);
        lines_to_tab(lines);
    }

    private void lines_open_three_check(){ //метод открытой тройки для всех строк таблицы
        Metods[] lines = new Metods[10];
        lines = tab_to_lines(lines);
        lines = cells_open_three_check(lines);
        lines_to_tab(lines);
    }

    private void lines_open_four_check(){ //метод открытой четверки для всех строк таблицы
        Metods[] lines = new Metods[10];
        lines = tab_to_lines(lines);
        lines = cells_open_four_check(lines);
        lines_to_tab(lines);
    }

    private void lines_close_two_check(){ //метод закрытой пары для всех строк таблицы
        Metods[] lines = new Metods[10];
        lines = tab_to_lines(lines);
        lines = cells_close_two_check(lines);
        lines_to_tab(lines);
    }

    private void lines_close_three_check(){ //метод скрытой тройки для всех строк таблицы
        Metods[] lines = new Metods[10];
        lines = tab_to_lines(lines);
        lines = cells_close_three_check(lines);
        lines_to_tab(lines);
    }

    private void lines_close_four_check(){ //метод скрытой четверки для всех строк таблицы
        Metods[] lines = new Metods[10];
        lines = tab_to_lines(lines);
        lines = cells_close_four_check(lines);
        lines_to_tab(lines);
    }

    private void lines_indicStrColSqr_check(){ //метод указывающей пары\тройки для всех строк
        Metods[] lines = new Metods[10];
        lines = tab_to_lines(lines);
        lines = cells_indicStrColSqr_check(lines);
        lines_to_tab(lines);
    }

    private void lines_to_tab(Metods[] lines){ //из массива строк ячеек создает строку таблицы
        for(int i=1; i<10; i++){
            t=lines[i].get_cells();
            for(int j=1; j<10; j++){
                tabl[i][j] = t[j];
            }
        }
    }

    //endregion



    //region Работа со столбцами

    private Metods[] tab_to_columns(Metods[] columns){ //создает массив строк из ячеек столбцов таблицы
        for(int i=1; i<10; i++){
            for(int j=1; j<10; j++){
                t[j]=new Cell();
                t[j]=tabl[j][i];
            }
            columns[i]=new Metods(t);
        }
        return columns;
    }

    private void columns_easy_check(){
        Metods[] columns = new Metods[10];
        columns = tab_to_columns(columns);
        columns = cells_easy_check(columns);
        columns_to_tab(columns);
    }

    private void columns_single_check(){
        Metods[] columns = new Metods[10];
        columns = tab_to_columns(columns);
        columns = cells_single_check(columns);
        columns_to_tab(columns);
    }

    private void columns_open_two_check(){
        Metods[] columns = new Metods[10];
        columns = tab_to_columns(columns);
        columns = cells_open_two_check(columns);
        columns_to_tab(columns);
    }

    private void columns_open_three_check(){
        Metods[] columns = new Metods[10];
        columns = tab_to_columns(columns);
        columns = cells_open_three_check(columns);
        columns_to_tab(columns);
    }

    private void columns_open_four_check(){
        Metods[] columns = new Metods[10];
        columns = tab_to_columns(columns);
        columns = cells_open_four_check(columns);
        columns_to_tab(columns);
    }

    private void columns_close_two_check(){
        Metods[] columns = new Metods[10];
        columns = tab_to_columns(columns);
        columns = cells_close_two_check(columns);
        columns_to_tab(columns);
    }

    private void columns_close_three_check(){
        Metods[] columns = new Metods[10];
        columns = tab_to_columns(columns);
        columns = cells_close_three_check(columns);
        columns_to_tab(columns);
    }

    private void columns_close_four_check(){
        Metods[] columns = new Metods[10];
        columns = tab_to_columns(columns);
        columns = cells_close_four_check(columns);
        columns_to_tab(columns);
    }

    private void columns_indicStrColSqr_check(){
        Metods[] columns = new Metods[10];
        columns = tab_to_columns(columns);
        columns = cells_indicStrColSqr_check(columns);
        columns_to_tab(columns);
    }

    private void columns_to_tab(Metods[] columns){
        for(int i=1; i<10; i++){
            t=columns[i].get_cells();
            for(int j=1; j<10; j++){
                tabl[j][i] = t[j];
            }
        }
    }

    //endregion



    //region Работа с квадратами

    private Metods[] tab_to_squares(Metods[] squares){ //создает массив строк из ячеек квадратов таблицы
        int x1;
        int x2;
        int y1=1;
        int y2=4;
        int r=0;
        int a;
        for(int q=0; q<3; q++) {
            x1=1; x2=4;
            for (int u=0; u<3; u++) {
                a = 0; r++;
                for (int i = x1; i < x2; i++) {
                    for (int j = y1; j < y2; j++) {
                        a++;
                        t[a] = tabl[i][j];
                    }
                }
                squares[r] = new Metods(t);
                x1 = x1 + 3;
                x2 = x2 + 3;
            }
            y1=y1+3;
            y2=y2+3;
        }
        return squares;
    }

    private void squares_easy_check(){
        Metods[] squares = new Metods[10];
        squares = tab_to_squares(squares);
        squares = cells_easy_check(squares);
        squares_to_tab(squares);
    }

    private void squares_single_check(){
        Metods[] squares = new Metods[10];
        squares = tab_to_squares(squares);
        squares = cells_single_check(squares);
        squares_to_tab(squares);
    }

    private void squares_open_two_check(){
        Metods[] squares = new Metods[10];
        squares = tab_to_squares(squares);
        squares = cells_open_two_check(squares);
        squares_to_tab(squares);
    }

    private void squares_open_three_check(){
        Metods[] squares = new Metods[10];
        squares = tab_to_squares(squares);
        squares = cells_open_three_check(squares);
        squares_to_tab(squares);
    }

    private void squares_open_four_check(){
        Metods[] squares = new Metods[10];
        squares = tab_to_squares(squares);
        squares = cells_open_four_check(squares);
        squares_to_tab(squares);
    }

    private void squares_close_two_check(){
        Metods[] squares = new Metods[10];
        squares = tab_to_squares(squares);
        squares = cells_close_two_check(squares);
        squares_to_tab(squares);
    }

    private void squares_close_three_check(){
        Metods[] squares = new Metods[10];
        squares = tab_to_squares(squares);
        squares = cells_close_three_check(squares);
        squares_to_tab(squares);
    }

    private void squares_close_four_check(){
        Metods[] squares = new Metods[10];
        squares = tab_to_squares(squares);
        squares = cells_close_four_check(squares);
        squares_to_tab(squares);
    }

    private void squares_indicSqrStrCol_check(){
        Metods[] squares = new Metods[10];
        squares = tab_to_squares(squares);
        squares = cells_indicSqrStrCol_check(squares);
        squares_to_tab(squares);
    }

    private void squares_to_tab(Metods[] squares){
        int y1=1;
        int y2=4;
        int r=0;
        int x1, x2;
        int a=0;
        for(int q=0; q<3; q++) {
            x1=1; x2=4;
            for (int u=0; u<3; u++) {
                a = 0; r++;
                t=squares[r].get_cells();
                for (int i = x1; i < x2; i++) {
                    for (int j = y1; j < y2; j++) {
                        a++;
                        tabl[i][j] = t[a];
                    }
                }
                x1 = x1 + 3;
                x2 = x2 + 3;
            }
            y1=y1+3;
            y2=y2+3;
        }
    }

    //endregion



    //region METALGORITM
    void getAnswer(){ //мета алгоритм решения
        int n=has_chenged();
        for(int i=1; i<1000; i++){
            if (finish()!=81){
                easy_circle_check();
                if(finish()==81) break;
                n=has_chenged();
                single_check_tabl();
                if(has_chenged()!=n) continue;
                open_two_check_tabl();
                if(has_chenged()!=n) continue;
                open_three_check_tabl();
                if(has_chenged()!=n) continue;
                open_four_check_tabl();
                if(has_chenged()!=n) continue;
                close_two_check_tabl();
                if(has_chenged()!=n) continue;
                close_three_check_tabl();
                if(has_chenged()!=n) continue;
                close_four_check_tabl();
                if(has_chenged()!=n) continue;
                indicSqrStrCol_chek_tabl();
                if(has_chenged()!=n) continue;
                indicStrColSqr_check_tabl();
                if(has_chenged()!=n) continue;
                break;
            }
            else break;
        }
    }

    private void easy_circle_check(){ //простая зацикленная проверка для всей таблицы
        int n;
        do{
            n=has_chenged();
            easy_check_tabl();
        }while (n!=has_chenged());
    }

    private void easy_check_tabl(){ //простая проверка для всей таблицы
        int n = has_chenged();
        lines_easy_check();
        if (n!=has_chenged()) return; //проверка на изменения
        columns_easy_check();
        if (n!=has_chenged()) return;
        squares_easy_check();
    }

    private void single_check_tabl(){
        int n = has_chenged();
        lines_single_check();
        if (n!=has_chenged()) return;
        columns_single_check();
        if (n!=has_chenged()) return;
        squares_single_check();
    }

    private void open_two_check_tabl(){
        int n = has_chenged();
        lines_open_two_check();
        if (n!=has_chenged()) return;
        columns_open_two_check();
        if (n!=has_chenged()) return;
        squares_open_two_check();
    }

    private void open_three_check_tabl(){
        int n = has_chenged();
        lines_open_three_check();
        if (n!=has_chenged()) return;
        columns_open_three_check();
        if (n!=has_chenged()) return;
        squares_open_three_check();
    }

    private void open_four_check_tabl(){
        int n = has_chenged();
        lines_open_four_check();
        if (n!=has_chenged()) return;
        columns_open_four_check();
        if (n!=has_chenged()) return;
        squares_open_four_check();
    }

    private void close_two_check_tabl(){
        int n = has_chenged();
        lines_close_two_check();
        if (n!=has_chenged()) return;
        columns_close_two_check();
        if (n!=has_chenged()) return;
        squares_close_two_check();
    }

    private void close_three_check_tabl(){
        int n = has_chenged();
        lines_close_three_check();
        if (n!=has_chenged()) return;
        columns_close_three_check();
        if (n!=has_chenged()) return;
        squares_close_three_check();
    }

    private void close_four_check_tabl(){
        int n = has_chenged();
        lines_close_four_check();
        if (n!=has_chenged()) return;
        columns_close_four_check();
        if (n!=has_chenged()) return;
        squares_close_four_check();
    }

    private void indicSqrStrCol_chek_tabl(){ //указывающие пары\тройки только для квадратов
        squares_indicSqrStrCol_check();
    }

    private void indicStrColSqr_check_tabl(){ //указывающие пары\тройки только для строк и столбцов
        int n = has_chenged();
        lines_indicStrColSqr_check();
        if (n!=has_chenged()) return;
        columns_indicStrColSqr_check();
    }
    //endregion



    //region Методы решения
    private Metods[] cells_easy_check(Metods[] a){ //применяет простую проверку на каждую строку массива
        for(int i=1; i<10; i++){
            int n = a[i].getNvars();
            a[i].easy_check();
            if(n!=a[i].getNvars()) break;
        }
        return a;
    }

    private Metods[] cells_single_check(Metods[] a){
        for(int i=1; i<10; i++){
            int n = a[i].getNvars();
            a[i].single_check();
            if(n!=a[i].getNvars()) break;
        }
        return a;
    }

    private Metods[] cells_open_two_check(Metods[] a){
        for(int i=1; i<10; i++){
            int n = a[i].getNvars();
            a[i].open_two_check();
            if(n!=a[i].getNvars()) break;
        }
        return a;
    }

    private Metods[] cells_open_three_check(Metods[] a){
        for(int i=1; i<10; i++){
            int n = a[i].getNvars();
            a[i].open_three_check();
            if(n!=a[i].getNvars()) break;
        }
        return a;
    }

    private Metods[] cells_open_four_check(Metods[] a){
        for(int i=1; i<10; i++){
            int n = a[i].getNvars();
            a[i].open_four_check();
            if(n!=a[i].getNvars()) break;
        }
        return a;
    }

    private Metods[] cells_close_two_check(Metods[] a){
        for(int i=1; i<10; i++){
            int n = a[i].getNvars();
            a[i].close_two_check();
            if(n!=a[i].getNvars()) break;
        }
        return a;
    }

    private Metods[] cells_close_three_check(Metods[] a){
        for(int i=1; i<10; i++){
            int n = a[i].getNvars();
            a[i].close_three_check();
            if(n!=a[i].getNvars()) break;
        }
        return a;
    }

    private Metods[] cells_close_four_check(Metods[] a){
        for(int i=1; i<10; i++){
            int n = a[i].getNvars();
            a[i].close_four_check();
            if(n!=a[i].getNvars()) break;
        }
        return a;
    }

    private Metods[] cells_indicSqrStrCol_check(Metods[] a){
        for(int i=1; i<10; i++){
            int n = a[i].getNvars();
            a[i].indicSqrStrCol_check();
            if(n!=a[i].getNvars()) break;
        }
        return a;
    }

    private Metods[] cells_indicStrColSqr_check(Metods[] a){
        for(int i=1; i<10; i++){
            int n = a[i].getNvars();
            a[i].indicStrColSqr_check();
            if(n!=a[i].getNvars()) break;
        }
        return a;
    }
    //endregion


    boolean check_error(){
        Metods[] block = new Metods[10];
        block = tab_to_lines(block);
        for(int i=1; i<10; i++){
            if(block[i].check_error()) return true;
        }
        block = new Metods[10];
        block = tab_to_columns(block);
        for(int i=1; i<10; i++){
            if(block[i].check_error()) return true;
        }
        block = new Metods[10];
        block = tab_to_squares(block);
        for(int i=1; i<10; i++){
            if(block[i].check_error()) return true;
        }
        return false;
    }

    private int finish(){ //проверяет сколько ячеек таблицы имеют истинное значение
        int a=0;
        for(int i=1; i<10; i++){
            for(int j=1; j<10; j++){
                if(tabl[i][j].check_true()) a++;
            }
        }
        return a;
    }

    private int has_chenged(){ //возвращает количество возможных вариантов значений для всей таблицы
        int n=0;
        for(int i=1; i<10; i++){
            for(int j=1; j<10; j++){
                n=n+tabl[i][j].get_vars();
            }
        }
       return n;
    }
}
