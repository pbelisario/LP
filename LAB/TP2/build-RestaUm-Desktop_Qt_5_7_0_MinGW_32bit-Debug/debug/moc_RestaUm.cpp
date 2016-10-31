/****************************************************************************
** Meta object code from reading C++ file 'RestaUm.h'
**
** Created by: The Qt Meta Object Compiler version 67 (Qt 5.7.0)
**
** WARNING! All changes made in this file will be lost!
*****************************************************************************/

#include "../../RestaUm/RestaUm.h"
#include <QtCore/qbytearray.h>
#include <QtCore/qmetatype.h>
#if !defined(Q_MOC_OUTPUT_REVISION)
#error "The header file 'RestaUm.h' doesn't include <QObject>."
#elif Q_MOC_OUTPUT_REVISION != 67
#error "This file was generated using the moc from 5.7.0. It"
#error "cannot be used with the include files from this version of Qt."
#error "(The moc has changed too much.)"
#endif

QT_BEGIN_MOC_NAMESPACE
struct qt_meta_stringdata_RestaUm_t {
    QByteArrayData data[19];
    char stringdata0[190];
};
#define QT_MOC_LITERAL(idx, ofs, len) \
    Q_STATIC_BYTE_ARRAY_DATA_HEADER_INITIALIZER_WITH_OFFSET(len, \
    qptrdiff(offsetof(qt_meta_stringdata_RestaUm_t, stringdata0) + ofs \
        - idx * sizeof(QByteArrayData)) \
    )
static const qt_meta_stringdata_RestaUm_t qt_meta_stringdata_RestaUm = {
    {
QT_MOC_LITERAL(0, 0, 7), // "RestaUm"
QT_MOC_LITERAL(1, 8, 8), // "gameOver"
QT_MOC_LITERAL(2, 17, 0), // ""
QT_MOC_LITERAL(3, 18, 4), // "play"
QT_MOC_LITERAL(4, 23, 12), // "mostrarSobre"
QT_MOC_LITERAL(5, 36, 14), // "mostrarFimJogo"
QT_MOC_LITERAL(6, 51, 9), // "fillBoard"
QT_MOC_LITERAL(7, 61, 10), // "emptyBoard"
QT_MOC_LITERAL(8, 72, 10), // "trocarModo"
QT_MOC_LITERAL(9, 83, 8), // "QAction*"
QT_MOC_LITERAL(10, 92, 4), // "modo"
QT_MOC_LITERAL(11, 97, 15), // "modoTradicional"
QT_MOC_LITERAL(12, 113, 8), // "modoCruz"
QT_MOC_LITERAL(13, 122, 8), // "modoMais"
QT_MOC_LITERAL(14, 131, 13), // "modoBanquinho"
QT_MOC_LITERAL(15, 145, 10), // "modoFlecha"
QT_MOC_LITERAL(16, 156, 12), // "modoPiramide"
QT_MOC_LITERAL(17, 169, 11), // "modoLosango"
QT_MOC_LITERAL(18, 181, 8) // "novoJogo"

    },
    "RestaUm\0gameOver\0\0play\0mostrarSobre\0"
    "mostrarFimJogo\0fillBoard\0emptyBoard\0"
    "trocarModo\0QAction*\0modo\0modoTradicional\0"
    "modoCruz\0modoMais\0modoBanquinho\0"
    "modoFlecha\0modoPiramide\0modoLosango\0"
    "novoJogo"
};
#undef QT_MOC_LITERAL

static const uint qt_meta_data_RestaUm[] = {

 // content:
       7,       // revision
       0,       // classname
       0,    0, // classinfo
      15,   14, // methods
       0,    0, // properties
       0,    0, // enums/sets
       0,    0, // constructors
       0,       // flags
       1,       // signalCount

 // signals: name, argc, parameters, tag, flags
       1,    0,   89,    2, 0x06 /* Public */,

 // slots: name, argc, parameters, tag, flags
       3,    0,   90,    2, 0x08 /* Private */,
       4,    0,   91,    2, 0x08 /* Private */,
       5,    0,   92,    2, 0x08 /* Private */,
       6,    0,   93,    2, 0x08 /* Private */,
       7,    0,   94,    2, 0x08 /* Private */,
       8,    1,   95,    2, 0x08 /* Private */,
      11,    0,   98,    2, 0x08 /* Private */,
      12,    0,   99,    2, 0x08 /* Private */,
      13,    0,  100,    2, 0x08 /* Private */,
      14,    0,  101,    2, 0x08 /* Private */,
      15,    0,  102,    2, 0x08 /* Private */,
      16,    0,  103,    2, 0x08 /* Private */,
      17,    0,  104,    2, 0x08 /* Private */,
      18,    0,  105,    2, 0x08 /* Private */,

 // signals: parameters
    QMetaType::Void,

 // slots: parameters
    QMetaType::Void,
    QMetaType::Void,
    QMetaType::Void,
    QMetaType::Void,
    QMetaType::Void,
    QMetaType::Void, 0x80000000 | 9,   10,
    QMetaType::Void,
    QMetaType::Void,
    QMetaType::Void,
    QMetaType::Void,
    QMetaType::Void,
    QMetaType::Void,
    QMetaType::Void,
    QMetaType::Void,

       0        // eod
};

void RestaUm::qt_static_metacall(QObject *_o, QMetaObject::Call _c, int _id, void **_a)
{
    if (_c == QMetaObject::InvokeMetaMethod) {
        RestaUm *_t = static_cast<RestaUm *>(_o);
        Q_UNUSED(_t)
        switch (_id) {
        case 0: _t->gameOver(); break;
        case 1: _t->play(); break;
        case 2: _t->mostrarSobre(); break;
        case 3: _t->mostrarFimJogo(); break;
        case 4: _t->fillBoard(); break;
        case 5: _t->emptyBoard(); break;
        case 6: _t->trocarModo((*reinterpret_cast< QAction*(*)>(_a[1]))); break;
        case 7: _t->modoTradicional(); break;
        case 8: _t->modoCruz(); break;
        case 9: _t->modoMais(); break;
        case 10: _t->modoBanquinho(); break;
        case 11: _t->modoFlecha(); break;
        case 12: _t->modoPiramide(); break;
        case 13: _t->modoLosango(); break;
        case 14: _t->novoJogo(); break;
        default: ;
        }
    } else if (_c == QMetaObject::IndexOfMethod) {
        int *result = reinterpret_cast<int *>(_a[0]);
        void **func = reinterpret_cast<void **>(_a[1]);
        {
            typedef void (RestaUm::*_t)();
            if (*reinterpret_cast<_t *>(func) == static_cast<_t>(&RestaUm::gameOver)) {
                *result = 0;
                return;
            }
        }
    }
}

const QMetaObject RestaUm::staticMetaObject = {
    { &QMainWindow::staticMetaObject, qt_meta_stringdata_RestaUm.data,
      qt_meta_data_RestaUm,  qt_static_metacall, Q_NULLPTR, Q_NULLPTR}
};


const QMetaObject *RestaUm::metaObject() const
{
    return QObject::d_ptr->metaObject ? QObject::d_ptr->dynamicMetaObject() : &staticMetaObject;
}

void *RestaUm::qt_metacast(const char *_clname)
{
    if (!_clname) return Q_NULLPTR;
    if (!strcmp(_clname, qt_meta_stringdata_RestaUm.stringdata0))
        return static_cast<void*>(const_cast< RestaUm*>(this));
    return QMainWindow::qt_metacast(_clname);
}

int RestaUm::qt_metacall(QMetaObject::Call _c, int _id, void **_a)
{
    _id = QMainWindow::qt_metacall(_c, _id, _a);
    if (_id < 0)
        return _id;
    if (_c == QMetaObject::InvokeMetaMethod) {
        if (_id < 15)
            qt_static_metacall(this, _c, _id, _a);
        _id -= 15;
    } else if (_c == QMetaObject::RegisterMethodArgumentMetaType) {
        if (_id < 15)
            *reinterpret_cast<int*>(_a[0]) = -1;
        _id -= 15;
    }
    return _id;
}

// SIGNAL 0
void RestaUm::gameOver()
{
    QMetaObject::activate(this, &staticMetaObject, 0, Q_NULLPTR);
}
QT_END_MOC_NAMESPACE
