#include "Spell.h"
#include "Wizard.h"
#include "Hobbit.h"
#include <iostream>
#include <string>

using namespace std;

Hobbit::Hobbit(string n = "Bilbo Baggins")
{
    name = n;
}

Hobbit::~Hobbit(){}

void Hobbit::setName(string n)
{
    name = n;
}

string Hobbit::getName() const
{
    return name;
}
