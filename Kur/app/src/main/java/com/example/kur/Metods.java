package com.example.kur;

import java.util.Arrays;

class Metods { //класс методов решения
    private Cell[] cells; //массив ячеек (строка)

    Metods(Cell[] a){ //конструктор
        cells=Arrays.copyOf(a, a.length);
    }

    void easy_check(){ //простая проверка (удаляет из возможных вариантов значения ячейки значения, которые уже есть в строке)
        for(int i=1; i<10; i++){
            if (cells[i].check_true()){
                for(int j=1; j<10; j++){
                    if (j!=i) {
                        cells[j].del_var(cells[i].get_truevar());
                        cells[j].check_var();
                    }
                }
            }
        }
    }

    void single_check(){ //если среди вариантов ячейки есть такой, которого нет среди вариантов других ячеек, то это истинное значение ячейки (единственный вариант)
        easy_check();
        int[] a=new int[10];
        for(int i=1; i<10; i++){
            if (!(cells[i].check_true())){
                for(int j=1; j<10; j++){
                    if (cells[i].check_svar(j)) a[j]++;
                }
            }
        }
        for(int i=1; i<10; i++){
            if (a[i]==1){
                for(int j=1; j<10; j++){
                    if (cells[j].check_svar(i)) {
                        cells[j].set_truevar(i);
                    }
                }
            }
        }
    }

    void open_two_check(){ //метод открытой пары
        int[] a=new int[10];
        int p;
        for(int i=1; i<10; i++) a[i]=cells[i].get_vars();
        for(int i=1; i<10; i++){
            if (a[i]==2){
                for(int j=i+1; j<10; j++){
                    if (cells[j].vars()==cells[i].vars()){
                        for(int q=1; q<10; q++){
                            if ((q!=i)&&(q!=j)){
                                p=cells[i].vars();
                                cells[q].del_var(p%10);
                                p=(p-p%10)/10;
                                cells[q].del_var(p%10);
                                cells[q].check_var();
                                p=0;
                            }
                        }
                        for(int k=1; k<10; k++) a[k]=cells[k].get_vars();
                    }
                }
            }
        }
    }

    void open_three_check(){ //метод открытой тройки
        for(int i=1; i<10; i++){
            if ((cells[i].get_vars()<4)&&(cells[i].get_vars()>1)){
                for(int j=i+1; j<10; j++){
                    if ((cells[j].get_vars()<4)&&(cells[j].get_vars()>1)){
                        for(int q=j+1; q<10; q++){
                            if ((cells[q].get_vars()<4)&&(cells[q].get_vars()>1)){
                                int c;
                                int[] a = new int[10];
                                c=cells[i].vars();
                                for(int n=0; n<cells[i].get_vars(); n++){
                                    a[c%10]++;
                                    c=(c-c%10)/10;
                                }
                                c=cells[j].vars();
                                for(int n=0; n<cells[j].get_vars(); n++){
                                    a[c%10]++;
                                    c=(c-c%10)/10;
                                }
                                c=cells[q].vars();
                                for(int n=0; n<cells[q].get_vars(); n++){
                                    a[c%10]++;
                                    c=(c-c%10)/10;
                                }
                                c=0;
                                int l=0;
                                for(int n=1; n<10; n++){
                                    if(a[n]>0) c++;
                                    if(a[n]>1) l++;
                                }
                                if((c==3)&&(l==3)){
                                    for(int n=1; n<10; n++){
                                        if (a[n]>1){
                                            for(int m=1; m<10; m++){
                                                if((m!=i)&&(m!=j)&&(m!=q)){
                                                    cells[m].del_var(n);
                                                    cells[m].check_var();
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    void open_four_check(){ //метод открытой четверки
        for(int i=1; i<10; i++){
            if ((cells[i].get_vars()<5)&&(cells[i].get_vars()>1)){
                for(int j=i+1; j<10; j++){
                    if ((cells[j].get_vars()<5)&&(cells[j].get_vars()>1)){
                        for(int q=j+1; q<10; q++){
                            if ((cells[q].get_vars()<5)&&(cells[q].get_vars()>1)) {
                                for (int p = q+1; p<10; p++) {
                                    if((cells[p].get_vars()<5)&&(cells[p].get_vars()>1)) {
                                        int c;
                                        int[] a = new int[10];
                                        c = cells[i].vars();
                                        for (int n = 0; n < cells[i].get_vars(); n++) {
                                            a[c % 10]++;
                                            c = (c - c % 10) / 10;
                                        }
                                        c = cells[j].vars();
                                        for (int n = 0; n < cells[j].get_vars(); n++) {
                                            a[c % 10]++;
                                            c = (c - c % 10) / 10;
                                        }
                                        c = cells[q].vars();
                                        for (int n = 0; n < cells[q].get_vars(); n++) {
                                            a[c % 10]++;
                                            c = (c - c % 10) / 10;
                                        }
                                        c = cells[p].vars();
                                        for (int n = 0; n < cells[p].get_vars(); n++) {
                                            a[c % 10]++;
                                            c = (c - c % 10) / 10;
                                        }
                                        c = 0;
                                        int l=0;
                                        for (int n = 1; n < 10; n++) {
                                            if(a[n]>0) c++;
                                            if(a[n]>1) l++;
                                        }
                                        if ((c == 4)&&(l==4)) {
                                            for (int n = 1; n < 10; n++) {
                                                if (a[n]>1) {
                                                    for (int m = 1; m < 10; m++) {
                                                        if ((m != i) && (m != j) && (m != q) && (m!=p)) {
                                                            cells[m].del_var(n);
                                                            cells[m].check_var();
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    void close_two_check(){ //метод закрытой пары
        int[] a = new int[10];
        int l, c;

        for(int i=1; i<10; i++){
            c=cells[i].vars();
            l=cells[i].get_vars();
            for(int j=0; j<l; j++){
                a[c%10]++;
                c=(c-c%10)/10;
            }
        }
        for(int i=1; i<10; i++) {
            if (a[i] == 2) {
                for (int j=i+1; j<10; j++) {
                    if (a[j] == 2) {
                        for(int q=1; q<10; q++){
                            if((cells[q].check_svar(i))&&(cells[q].check_svar(j))){
                                for(int p=q+1; p<10; p++) {
                                    if ((cells[p].check_svar(i)) && (cells[p].check_svar(j))) {
                                        if((cells[p].get_vars()>2)||(cells[q].get_vars()>2)) {
                                            for (int t = 1; t < 10; t++) {
                                                if ((t != i) && (t != j)) {
                                                    cells[q].del_var(t);
                                                    cells[p].del_var(t);
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    void close_three_check(){ //метод скрытых троек
        int[] a = new int[10];
        int l, c;

        for(int i=1; i<10; i++){
            c=cells[i].vars();
            l=cells[i].get_vars();
            for(int j=0; j<l; j++){
                a[c%10]++;
                c=(c-c%10)/10;
            }
        }

        for(int i=1; i<10; i++){
            if((a[i]>1)&&(a[i]<4)){
                for(int j=i+1; j<10; j++){
                    if((a[j]>1)&&(a[j]<4)){
                        for(int q=j+1; q<10; q++){
                            if((a[q]>1)&&(a[q]<4)){
                                boolean[] b = new boolean[10];
                                for(int v=1; v<10; v++){
                                    if(cells[v].check_svar(i)) b[v]=true;
                                    if(cells[v].check_svar(j)) b[v]=true;
                                    if(cells[v].check_svar(q)) b[v]=true;
                                }
                                int n=0;
                                for(int v=1; v<10; v++) if(b[v]) n++;
                                if(n==3){
                                    for(int v=1; v<10; v++){
                                        if(b[v]){
                                            for(int w=1; w<10; w++){
                                                if((w!=i)&&(w!=j)&&(w!=q)){
                                                    cells[v].del_var(w);
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    void close_four_check() { //метод скрытых четверок
        int[] a = new int[10];
        int l, c;

        for(int i=1; i<10; i++){
            c=cells[i].vars();
            l=cells[i].get_vars();
            for(int j=0; j<l; j++){
                a[c%10]++;
                c=(c-c%10)/10;
            }
        }

        for(int i=1; i<10; i++){
            if((a[i]>1)&&(a[i]<5)){
                for(int j=i+1; j<10; j++){
                    if((a[j]>1)&&(a[j]<5)){
                        for(int q=j+1; q<10; q++){
                            if((a[q]>1)&&(a[q]<5)){
                                for(int r=q+1; r<10; r++){
                                    if((a[r]>1)&&(a[r]<5)){
                                        boolean[] b = new boolean[10];
                                        for(int v=1; v<10; v++){
                                            if(cells[v].check_svar(i)) b[v]=true;
                                            if(cells[v].check_svar(j)) b[v]=true;
                                            if(cells[v].check_svar(q)) b[v]=true;
                                            if(cells[v].check_svar(r)) b[v]=true;
                                        }
                                        int n=0;
                                        for(int v=1; v<10; v++) if(b[v]) n++;
                                        if(n==4){
                                            for(int v=1; v<10; v++){
                                                if(b[v]){
                                                    for(int w=1; w<10; w++){
                                                        if((w!=i)&&(w!=j)&&(w!=q)&&(w!=r)){
                                                            cells[v].del_var(w);
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    void indicSqrStrCol_check(){ //указыывающие пары и тройки для квадратов
        int[] a = new int[10];
        int l, c;

        for(int i=1; i<10; i++){
            c=cells[i].vars();
            l=cells[i].get_vars();
            for(int j=0; j<l; j++){
                a[c%10]++;
                c=(c-c%10)/10;
            }
        }

        for(int i=1; i<10; i++){
            if(a[i]==2){
                for(int j=1; j<10; j++){
                    if(cells[j].check_svar(i)){
                        for(int q=j+1; q<10; q++){
                            if(cells[q].check_svar(i)){
                                if(cells[j].getY()==cells[q].getY()){
                                    int y = cells[j].getY();
                                    for(int p=1; p<10; p++){
                                        if((p!=cells[j].getX())&&(p!=cells[q].getX())){
                                            Tabl.tabl[y][p].del_var(i);
                                            Tabl.tabl[y][p].check_var();
                                        }
                                    }
                                }
                                if(cells[j].getX()==cells[q].getX()){
                                    int x = cells[j].getX();
                                    for(int p=1; p<10; p++){
                                        if((p!=cells[j].getY())&&(p!=cells[q].getY())){
                                            Tabl.tabl[p][x].del_var(i);
                                            Tabl.tabl[p][x].check_var();
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            if(a[i]==3){
                for(int j=1; j<10; j++) {
                    if (cells[j].check_svar(i)) {
                        for (int q = j + 1; q < 10; q++) {
                            if (cells[q].check_svar(i)) {
                                for(int r=q+1; r<10; r++){
                                    if(cells[r].check_svar(i)){
                                        if((cells[j].getY()==cells[q].getY())&&(cells[j].getY()==cells[r].getY())){
                                            //System.out.println("in13S "+i+""+j+""+q+" "); print();System.out.println();
                                            int y = cells[j].getY();
                                            for(int p=1; p<10; p++){
                                                if((p!=cells[j].getX())&&(p!=cells[q].getX())&&(p!=cells[r].getX())){
                                                    Tabl.tabl[y][p].del_var(i);
                                                    Tabl.tabl[y][p].check_var();
                                                }
                                            }
                                        }
                                        if((cells[j].getX()==cells[q].getX())&&(cells[j].getX()==cells[r].getX())){
                                            //System.out.println("in13C "+i+""+j+""+q+" "); print();System.out.println();
                                            int x = cells[j].getX();
                                            for(int p=1; p<10; p++){
                                                if((p!=cells[j].getY())&&(p!=cells[q].getY())&&(p!=cells[r].getY())){
                                                    Tabl.tabl[p][x].del_var(i);
                                                    Tabl.tabl[p][x].check_var();
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    void indicStrColSqr_check(){ //указывающие пары и тройки для строк и столбцов
        int[] a = new int[10];
        int l, c;

        for(int i=1; i<10; i++){
            c=cells[i].vars();
            l=cells[i].get_vars();
            for(int j=0; j<l; j++){
                a[c%10]++;
                c=(c-c%10)/10;
            }
        }

        if(cells[1].getY()==cells[2].getY()){ //значит строка
            for(int i=1; i<10; i++) {
                if (a[i] == 2) {
                    for(int j=1; j<10; j++) {
                        if (cells[j].check_svar(i)) {
                            for (int q = j + 1; q < 10; q++) {
                                if (cells[q].check_svar(i)) {
                                    int w=25;
                                    int u=25;
                                    if(((0<cells[j].getX())&&(cells[j].getX()<4))&&((0<cells[q].getX())&&(cells[q].getX()<4))){
                                        w=1; u=4;
                                    }
                                    if(((3<cells[j].getX())&&(cells[j].getX()<7))&&((3<cells[q].getX())&&(cells[q].getX()<7))){
                                        w=4; u=7;
                                    }
                                    if(((6<cells[j].getX())&&(cells[j].getX()<10))&&((6<cells[q].getX())&&(cells[q].getX()<10))){
                                        w=7; u=10;
                                    }

                                    if(w<25) {
                                        int g = 25, h = 25;
                                        if (cells[j].getY() % 3 == 1) {
                                            g = 0;
                                            h = 3;
                                        }
                                        if (cells[j].getY() % 3 == 2) {
                                            g = 1;
                                            h = 1;
                                        }
                                        if (cells[j].getY() % 3 == 0) {
                                            g = 2;
                                            h = 0;
                                        }
                                        if(g<25) {
                                            for (int y = cells[j].getY() - g; y < cells[j].getY() + h; y++) {
                                                for (int x = w; x < u; x++) {
                                                    if ((y != cells[j].getY())) {
                                                        Tabl.tabl[y][x].del_var(i);
                                                        Tabl.tabl[y][x].del_var(i);
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                if (a[i] == 3) {
                    for(int j=1; j<10; j++) {
                        if (cells[j].check_svar(i)) {
                            for (int q = j + 1; q < 10; q++) {
                                if (cells[q].check_svar(i)) {
                                    for (int r = q + 1; r < 10; r++) {
                                        if (cells[r].check_svar(i)) {
                                            int w=25;
                                            int u=25;
                                            if(((0<cells[j].getX())&&(cells[j].getX()<4))&&((0<cells[r].getX())&&(cells[r].getX()<4))){
                                                w=1; u=4;
                                            }
                                            if(((3<cells[j].getX())&&(cells[j].getX()<7))&&((3<cells[r].getX())&&(cells[r].getX()<7))){
                                                w=4; u=7;
                                            }
                                            if(((6<cells[j].getX())&&(cells[j].getX()<10))&&((6<cells[r].getX())&&(cells[r].getX()<10))){
                                                w=7; u=10;
                                            }

                                            if(w<25) {
                                                int g = 25, h = 25;
                                                if (cells[j].getY() % 3 == 1) {
                                                    g = 0;
                                                    h = 3;
                                                }
                                                if (cells[j].getY() % 3 == 2) {
                                                    g = 1;
                                                    h = 1;
                                                }
                                                if (cells[j].getY() % 3 == 0) {
                                                    g = 2;
                                                    h = 0;
                                                }
                                                if(g<25) {
                                                    for (int y = cells[j].getY() - g; y < cells[j].getY() + h; y++) {
                                                        for (int x = w; x < u; x++) {
                                                            if ((y != cells[j].getY())) {
                                                                Tabl.tabl[y][x].del_var(i);
                                                                Tabl.tabl[y][x].del_var(i);
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        if(cells[1].getX()==cells[2].getX()){ //значит столбец
            for(int i=1; i<10; i++) {
                if (a[i] == 2) {
                    for(int j=1; j<10; j++) {
                        if (cells[j].check_svar(i)) {
                            for (int q = j + 1; q < 10; q++) {
                                if (cells[q].check_svar(i)) {
                                    int w=25;
                                    int u=25;
                                    if(((0<cells[j].getY())&&(cells[j].getY()<4))&&((0<cells[q].getY())&&(cells[q].getY()<4))){
                                        w=1; u=4;
                                    }
                                    if(((3<cells[j].getY())&&(cells[j].getY()<7))&&((3<cells[q].getY())&&(cells[q].getY()<7))){
                                        w=4; u=7;
                                    }
                                    if(((6<cells[j].getY())&&(cells[j].getY()<10))&&((6<cells[q].getY())&&(cells[q].getY()<10))){
                                        w=7; u=10;
                                    }
                                    if(w<25) {
                                        int g = 25, h = 25;
                                        if (cells[j].getX() % 3 == 1) {
                                            g = 0;
                                            h = 3;
                                        }
                                        if (cells[j].getX() % 3 == 2) {
                                            g = 1;
                                            h = 1;
                                        }
                                        if (cells[j].getX() % 3 == 0) {
                                            g = 2;
                                            h = 0;
                                        }
                                        if(g<25) {
                                            for (int y = w; y < u; y++) {
                                                for (int x = cells[j].getX() - g; x < cells[j].getX() + h; x++) {
                                                    if (x != cells[j].getX()) {
                                                        Tabl.tabl[y][x].del_var(i);
                                                        Tabl.tabl[y][x].del_var(i);
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                if (a[i] == 3) {
                    for(int j=1; j<10; j++) {
                        if (cells[j].check_svar(i)) {
                            for (int q = j + 1; q < 10; q++) {
                                if (cells[q].check_svar(i)) {
                                    for (int r = q + 1; r < 10; r++) {
                                        if (cells[r].check_svar(i)) {
                                            int w=25;
                                            int u=25;
                                            if(((0<cells[j].getY())&&(cells[j].getY()<4))&&((0<cells[r].getY())&&(cells[r].getY()<4))){
                                                w=1; u=4;
                                            }
                                            if(((3<cells[j].getY())&&(cells[j].getY()<7))&&((3<cells[r].getY())&&(cells[r].getY()<7))){
                                                w=4; u=7;
                                            }
                                            if(((6<cells[j].getY())&&(cells[j].getY()<10))&&((6<cells[r].getY())&&(cells[r].getY()<10))){
                                                w=7; u=10;
                                            }
                                            if(w<25) {
                                                int g = 25, h = 25;
                                                if (cells[j].getX() % 3 == 1) {
                                                    g = 0;
                                                    h = 3;
                                                }
                                                if (cells[j].getX() % 3 == 2) {
                                                    g = 1;
                                                    h = 1;
                                                }
                                                if (cells[j].getX() % 3 == 0) {
                                                    g = 2;
                                                    h = 0;
                                                }
                                                if(g<25) {
                                                    for (int y = w; y < u; y++) {
                                                        for (int x = cells[j].getX() - g; x < cells[j].getX() + h; x++) {
                                                            if (x != cells[j].getX()) {
                                                                Tabl.tabl[y][x].del_var(i);
                                                                Tabl.tabl[y][x].del_var(i);
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }



    int getNvars(){ //возвращает кол-во вариантов в строке\столбце\квадрате
        int n=0;
        for(int i=1; i<10; i++){
            n=n+cells[i].get_vars();
        }
        return n;
    }

    Cell[] get_cells(){ //возвращает массив ячеект
        return cells;
    }

    boolean check_error(){
        for(int i=1; i<10; i++){
            for(int j=1; j<10; j++){
                if((cells[i].get_truevar()==cells[j].get_truevar())&&(i!=j)&&(cells[i].get_truevar()!=0))
                    return true;
            }
        }
        return false;
    }

}
