package com.example.kur;

class Cell { //класс ячейка
    private boolean[] var; //возможные варианты значения ячейки
    private int truevar; //истинное значение ячейки
    private int x; //координаты ячейки в таблице
    private int y; //координаты ячейки в таблице

    Cell(){ //конструктор
        var=new boolean[10];
        truevar=0;
        for(int i=0; i<10; i++) var[i]=false;
    }

    Cell(int i, int j){ //второй конструктор
        y=i;
        x=j;
        var=new boolean[10];
        truevar=0;
        for(int q=0; q<10; q++) var[q]=false;
    }

    void first_set(int a){ //первая загруска значения в ячейку
        if (a==0){
            var[0]=false;
            for(int i=1; i<10; i++) var[i]=true;
        }
        else{
            var[0]=true;
            var[a]=true;
            truevar=a;
        }
    }

    void check_var(){ //проверка: если есть только один вариант значения ячейки, то он записывается, как истинное значение этой ячейки
        int a=0;
        int j=0;
        for(int i=1; i<10; i++){
            if (var[i]){
                a++;
                j=i;
            }
        }
        if (a==1){
            var[0]=true;
            truevar=j;
        }
        else var[0]=false;
    }

    boolean check_true(){ //проверка: есть ли уже у ячейки истинное значение или нет
        return var[0];
    }

    int get_truevar(){ //возвращает истинное значение ячейки
        return truevar;
    }

    void del_var(int a){ //удаляет из возможных вариантов значений ячейки значение а
        var[a]=false;
    }

    boolean check_svar(int a){ //проверка: есть ли значение а среди возможных вариантов значения ячейки
        return var[a];
    }

    void set_truevar(int a){ //устанавливает для ячейки истинное значчение
        for(int i=0; i<10; i++) var[i]=false;
        var[0]=true;
        var[a]=true;
        truevar=a;
    }

    int get_vars(){ //возвращает количество возможных вариантов значения ячейки
        int a=0;
        for(int i=1; i<10; i++){
            if (var[i]) a++;
        }
        return a;
    }

    int vars(){ //возвращает возможные варианты значения ячейки ввиде числа, где кажлое значение записано, как число единиц, десяток, сотен и тд
        int a=get_vars();
        int s=0;
        for(int i=1; i<10; i++){
            if (var[i]){
                a=a-1;
                s=s+i*(int)Math.pow(10, a);
            }
        }
        return s;
    }

    int getX(){
        return x;
    }

    int getY() {
        return y;
    }

}
