/*
 * Class Spell
 */

#ifndef SPELL_H
#define	SPELL_H

#include <string>
#include <iostream>
using namespace std;

class Spell {
private:
    string name;
    int difficultyLevel;
    int skillLevel;
public:
    Spell(string, int, int);
    Spell(const Spell& );
    ~Spell();
    void setName(string);
    string getName() const;
    void setDifficultyLevel(int );
    int getDifficultyLevel() const;
    void setSkillLevel(int);
    int getSkillLevel() const;
};

#endif	/* SPELL_H */

