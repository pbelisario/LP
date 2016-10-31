#include "RestaUm.h"
#include "ui_RestaUm.h"

#include <QDebug>
#include <QActionGroup>
#include <QMessageBox>

int MODO_JOGO = 0; //variavel para inicializar o modo de jogo anterior atraves do botao novo

RestaUm::RestaUm(QWidget *parent) :
    QMainWindow(parent),
    ui(new Ui::RestaUm) {

    ui->setupUi(this);

    //criar grupo para acoes na mesma aba
    QActionGroup* group = new QActionGroup(this);
    group->setExclusive(true);
    group->addAction(ui->actionTradicional);
    group->addAction(ui->actionCruz);
    group->addAction(ui->actionMais);
    group->addAction(ui->actionBanquinho);
    group->addAction(ui->actionFlecha);
    group->addAction(ui->actionPiramide);
    group->addAction(ui->actionLosango);

    //conectar botoes, acoes e funcoes
    QObject::connect(
            ui->actionNovo,
            SIGNAL(triggered()),
            this,
            SLOT(novoJogo()));

    QObject::connect(
        group,
        SIGNAL(triggered(QAction*)),
        this,
        SLOT(trocarModo(QAction*)));

    QObject::connect(
        ui->actionSair,
        SIGNAL(triggered()),
        qApp,
        SLOT(quit()));

    QObject::connect(
        ui->actionSobre,
        SIGNAL(triggered()),
        this,
        SLOT(mostrarSobre()));

    QObject::connect(
        this,
        SIGNAL(gameOver()),
        this,
        SLOT(mostrarFimJogo()));

    this->adjustSize();
    this->setFixedSize(this->size());
}

RestaUm::~RestaUm() {
    delete ui;
}

void RestaUm::play() {
    Peca* peca = qobject_cast<Peca*>(
                QObject::sender());
    int cont = 0;
    int i = QString(peca->objectName().at(4)).toInt();
    int j = QString(peca->objectName().at(5)).toInt();
    Peca* esq = NULL;
    Peca* dir = NULL;
    Peca* cima = NULL;
    Peca* baixo = NULL;
    if(this->findChild<Peca*>(QString("peca%1%2").arg(i).arg(j - 2)) != NULL){
        esq = this->findChild<Peca*>(QString("peca%1%2").arg(i).arg(j - 2));
    }
    if(this->findChild<Peca*>(QString("peca%1%2").arg(i).arg(j + 2))){
       dir = this->findChild<Peca*>(QString("peca%1%2").arg(i).arg(j + 2));
    }
    if(this->findChild<Peca*>(QString("peca%1%2").arg(i - 2).arg(j))){
        cima = this->findChild<Peca*>(QString("peca%1%2").arg(i - 2).arg(j));
    }
    if(this->findChild<Peca*>(QString("peca%1%2").arg(i + 2).arg(j))){
        baixo = this->findChild<Peca*>(QString("peca%1%2").arg(i + 2).arg(j));
    }
    if (false) {
        emit gameOver();
    } else {
        if(esq != NULL && esq->m_state == Peca::Empty && this->findChild<Peca*>(QString("peca%1%2").arg(i).arg(j - 1)) != NULL){
            //if() MEXER NESSE IF <<<<<<<<<<<<<<<<<<<<<<------------------------------------------------------------------------------------------------------------------------!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!







            esq->setState(Peca::Jumpable);
            cont++;
        }
        if(baixo != NULL && baixo->m_state == Peca::Empty){
            baixo->setState(Peca::Jumpable);
            cont++;
        }
        if(dir != NULL && dir->m_state == Peca::Empty){
            dir->setState(Peca::Jumpable);
            cont++;
        }
        if(cima != NULL && cima->m_state == Peca::Empty) {
            cima->setState(Peca::Jumpable);
            cont++;
        }
        if(cont > 1){

        } else {
            if(esq != NULL){
                peca->setState(Peca::Empty);
                this->findChild<Peca*>(QString("peca%1%2").arg(i).arg(j - 1))->m_state = Peca::Empty;
                esq->setState(Peca::Filled);
            } else if(dir != NULL){
                peca->setState(Peca::Empty);
                dir->setState(Peca::Filled);
            } else if(cima != NULL){
                peca->setState(Peca::Empty);
                cima->setState(Peca::Filled);
            } else if(baixo != NULL){
                peca->setState(Peca::Empty);
                baixo->setState(Peca::Filled);
            }
        }

    }

}

void RestaUm::mostrarSobre() {
    QMessageBox::information(this,
       tr("Sobre"),
       tr("Resta Um\n\nHigor Fischer de Paula Lopes\nPedro Frois Bittencourt"));
}


void RestaUm::mostrarFimJogo() {
    QMessageBox::information(this,
       tr("Fim"),
       tr("Parabéns, você venceu!"));
}


void RestaUm::trocarModo(QAction* modo) {
    if (modo == ui->actionTradicional){
        qDebug() << "modo: tradicional";
        modoTradicional();
    }
    else if (modo == ui->actionCruz){
        qDebug() << "modo: cruz";
        modoCruz();
    }
    else if (modo == ui->actionMais){
        qDebug() << "modo: mais";
        modoMais();
    }
    else if (modo == ui->actionBanquinho){
        qDebug() << "modo: banquinho";
        modoBanquinho();
    }
    else if (modo == ui->actionFlecha){
        qDebug() << "modo: flecha";
        modoFlecha();
    }
    else if (modo == ui->actionPiramide){
        qDebug() << "modo: piramide";
        modoPiramide();
    }
    else if (modo == ui->actionLosango){
        qDebug() << "modo: losango";
        modoLosango();
    }
}
//Enxer o tabuleiro com pecas
void RestaUm::fillBoard(){
    for (int r = 0; r < 7; r++) {
        for (int c = 0; c < 7; c++) {
            m_pecas[r][c] =
              this->findChild<Peca*>(
                QString("peca%1%2").arg(r).arg(c));
            if (m_pecas[r][c]) {
                QObject::connect(
                  m_pecas[r][c],
                  SIGNAL(clicked()),
                  this,
                  SLOT(play()));

                m_pecas[r][c]->setState(Peca::Filled);
            }
        }
    }
}

//Esvaziar o tabuleiro das pecas
void RestaUm::emptyBoard(){
    for (int r = 0; r < 7; r++) {
        for (int c = 0; c < 7; c++) {
            m_pecas[r][c] =
              this->findChild<Peca*>(
                QString("peca%1%2").arg(r).arg(c));
            if (m_pecas[r][c]) {
                QObject::connect(
                  m_pecas[r][c],
                  SIGNAL(clicked()),
                  this,
                  SLOT(play()));

                m_pecas[r][c]->setState(Peca::Empty);
            }
        }
    }
}

//Inicializar um novo jogo no mesmo modo que escolhido anteriormente
void RestaUm::novoJogo(){
    switch(MODO_JOGO){
        case 0:
            modoTradicional();
        break;

        case 1:
            modoCruz();
        break;

        case 2:
            modoMais();
        break;

        case 3:
            modoBanquinho();
        break;

        case 4:
            modoFlecha();
        break;

        case 5:
            modoPiramide();
        break;

        case 6:
            modoLosango();
        break;
        }
}

//Inicializacao dos formatos dos jogos e Atualaizacao da variavel global MODO_JOGO
void RestaUm::modoTradicional(){
    fillBoard();

    m_pecas[3][3]->setState(Peca::Empty);

    MODO_JOGO = 0;
}

void RestaUm::modoCruz(){
    emptyBoard();

    m_pecas[2][2]->setState(Peca::Filled);
    m_pecas[2][3]->setState(Peca::Filled);
    m_pecas[2][4]->setState(Peca::Filled);

    m_pecas[1][3]->setState(Peca::Filled);
    m_pecas[3][3]->setState(Peca::Filled);
    m_pecas[4][3]->setState(Peca::Filled);

    MODO_JOGO = 1;
}

void RestaUm::modoMais(){
    emptyBoard();

    int r = 3;
    for (int c = 1; c < 6; c++){
        m_pecas[r][c]->setState(Peca::Filled);
    }
    int c = 3;
    for (r = 1; r < 6; r++){
        if(r != 3) m_pecas[r][c]->setState(Peca::Filled);
    }

    MODO_JOGO = 2;
}

void RestaUm::modoBanquinho(){
    emptyBoard();

    for(int r = 0; r < 4; r++){
        for(int c = 2; c < 5; c++){
            m_pecas[r][c]->setState(Peca::Filled);
        }
    }

    m_pecas[3][3]->setState(Peca::Empty);

    MODO_JOGO = 3;
}

void RestaUm::modoFlecha(){
    emptyBoard();

    for(int r = 0; r < 7; r++){
        for(int c = 2; c < 5; c++){
            m_pecas[r][c]->setState(Peca::Filled);
        }
    }
    m_pecas[2][1]->setState(Peca::Filled);
    m_pecas[2][5]->setState(Peca::Filled);

    m_pecas[0][2]->setState(Peca::Empty);
    m_pecas[0][4]->setState(Peca::Empty);

    m_pecas[3][2]->setState(Peca::Empty);
    m_pecas[3][4]->setState(Peca::Empty);

    m_pecas[4][2]->setState(Peca::Empty);
    m_pecas[4][4]->setState(Peca::Empty);

    MODO_JOGO = 4;
}

void RestaUm::modoPiramide(){
    fillBoard();

    m_pecas[0][2]->setState(Peca::Empty);
    m_pecas[0][3]->setState(Peca::Empty);
    m_pecas[0][4]->setState(Peca::Empty);

    m_pecas[1][2]->setState(Peca::Empty);
    m_pecas[1][4]->setState(Peca::Empty);

    m_pecas[2][0]->setState(Peca::Empty);
    m_pecas[2][1]->setState(Peca::Empty);
    m_pecas[2][5]->setState(Peca::Empty);
    m_pecas[2][6]->setState(Peca::Empty);

    m_pecas[3][0]->setState(Peca::Empty);
    m_pecas[3][6]->setState(Peca::Empty);

    for(int r = 5; r < 7; r++){
        for(int c = 2; c < 5; c++){
            m_pecas[r][c]->setState(Peca::Empty);
        }
    }

    MODO_JOGO = 5;
}

void RestaUm::modoLosango(){
    fillBoard();

    m_pecas[0][2]->setState(Peca::Empty);
    m_pecas[0][4]->setState(Peca::Empty);

    m_pecas[2][0]->setState(Peca::Empty);
    m_pecas[2][6]->setState(Peca::Empty);

    m_pecas[3][3]->setState(Peca::Empty);

    m_pecas[4][0]->setState(Peca::Empty);
    m_pecas[4][6]->setState(Peca::Empty);

    m_pecas[6][2]->setState(Peca::Empty);
    m_pecas[6][4]->setState(Peca::Empty);

    MODO_JOGO = 6;
}
