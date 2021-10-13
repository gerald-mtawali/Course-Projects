/*
 * main function to test code
 */

#include <cstdlib>
#include <iostream>
#include "Wizard.h"
#include "Spell.h"
using namespace std;

int main() {
    Wizard wiz;
    Spell s1("vanishing",3,1);
    wiz.addSpell(s1);

    cout << "The name of the spell is: " << s1.getName() << endl;
    cout << "The number of spells in the pouch is: " << wiz.getNumberOfSpells() << endl;


    //wiz + s1;

    //test your other code here
    return 0;
}

