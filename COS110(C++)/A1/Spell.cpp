#include "Spell.h"
#include "Wizard.h"
#include "Hobbit.h"
#include <iostream>
#include <string>

using namespace std;

Spell::Spell(string n="Unknown", int d=10, int s = 5)
{
    name = n;
    difficultyLevel = d;
    skillLevel = s;
}

Spell::Spell(const Spell& sp)
{
    name = sp.name;
    difficultyLevel = sp.difficultyLevel;
    skillLevel = sp.skillLevel;
}

Spell::~Spell()
{
}

void Spell::setName(string n)
{
    name = n;
}

string Spell::getName() const
{
    return name;

}

void Spell::setDifficultyLevel(int d)
{
    difficultyLevel = d;
}

int Spell::getDifficultyLevel() const
{
    return difficultyLevel;
}

void Spell::setSkillLevel(int s)
{
    skillLevel = s;
}

int Spell::getSkillLevel() const
{
    return skillLevel;
}
