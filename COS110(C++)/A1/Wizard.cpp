#include "Spell.h"
#include "Wizard.h"
#include "Hobbit.h"
#include <iostream>
#include <string>

using namespace std;

Wizard::Wizard()
{
    spells = new Spell[maxNumberOfSpells];

    for(int i = 0; i < maxNumberOfSpells; i++)
        spells[i] = " ";
    numberOfSpells = 0;
    maxNumberOfSpells = 10;
    hasBeenSummoned = false;
    age = 20;
    numberOfLossedSpells = 0;
    hasCompletedTraining = false;
}

Wizard::Wizard(const Wizard& w)
{
    numberOfSpells = w.numberOfSpells;
    numberOfLossedSpells = w.numberOfLossedSpells;
    maxNumberOfSpells = w.maxNumberOfSpells;
    hasBeenSummoned = w.hasBeenSummoned;
    age = w.age;
    hasCompletedTraining = w.hasCompletedTraining;

    spells = new Spell[maxNumberOfSpells];
    for(int i =0; i < maxNumberOfSpells; i++)
        spells[i] = w.spells[i];
}

Wizard::~Wizard()
{
    delete [] spells;
}

void Wizard::addSpell(const Spell& s)
{
    Spell *temp = new Spell[maxNumberOfSpells];
    if(numberOfSpells < maxNumberOfSpells)
    {
        for(int i = 0; i < maxNumberOfSpells; i++)//create a deep copy
        {
            temp[i+1] = spells[i];
        }
        temp[0] = s;//set temp[0] to the spell argument

        for(int i = 0; i < maxNumberOfSpells; i++)
        {
            spells[i] = temp[i];
        }
        numberOfSpells++;

        delete [] temp;
    }

    else if(numberOfSpells == maxNumberOfSpells)
    {
        numberOfSpells++;
        maxNumberOfSpells++;
        spells[(maxNumberOfSpells - 1)] = s;
    }

}

void Wizard::deleteSpell(string name)
{
    for(int i = 0; i < numberOfSpells; i++)
    {
        if(spells[i].getName() =name)
        {
            delete spells[i];
        }
    }
    numberOfSpells--;
    numberOfLossedSpells++;
}

int Wizard::getNumberOfSpells() const
{
    return numberOfSpells;
}

void Wizard::setMaxNumberOfSpells(int m)
{
    maxNumberOfSpells = m;
}

int Wizard::getMaxNumberOfSpells() const
{
    return maxNumberOfSpells;
}

void Wizard::setAge(int a)
{
    age = a;
}

int Wizard::getAge() const
{
    return age;
}

int Wizard::getNumberOfLossedSpells() const
{
    return numberOfLossedSpells;
}

Spell& getSpell(int index) const
{
    for(int i = 0; i < numberOfSpells; i++)
    {
        if(i == index)
            return spells[i];
    }
}
