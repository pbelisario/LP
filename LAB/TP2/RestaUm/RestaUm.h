#ifndef RESTAUM_H
#define RESTAUM_H

#include <Peca.h>
#include <QMainWindow>

namespace Ui {
    class RestaUm;
}

class RestaUm : public QMainWindow {
    Q_OBJECT

public:
    explicit RestaUm(QWidget *parent = 0);
    ~RestaUm();

signals:
    void gameOver();

private:
    Ui::RestaUm *ui;
    Peca* m_pecas[7][7];

private slots:
    void play();
    void mostrarSobre();
    void mostrarFimJogo();
    void fillBoard();
    void emptyBoard();
    void trocarModo(QAction* modo);
    void modoTradicional();
    void modoCruz();
    void modoMais();
    void modoBanquinho();
    void modoFlecha();
    void modoPiramide();
    void modoLosango();
    void novoJogo();


};

#endif // RESTAUM_H
