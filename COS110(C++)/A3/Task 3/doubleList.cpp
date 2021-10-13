//Provide the implementation for the DoubleList class in this file.
#include "doubleList.h"

template<class T>
ostream& operator<<(ostream& os, DoubleList<T>& ll) {
    int listSize = ll.size();
    if(isEmpty())
        os << "[]";
    else
    {
        os << "[";
        for(int i = 0; i < listSize; i++)
        {
            os << ll->data;
            if(i < (listSize-1))
                os << ",";
        }
        os << "]";
    }
    return os;
}

}

/*constructor*/
template<class T>
DoubleList<T>::DoubleList(){
    dHead = nullptr;
}

/*copy constructor*/
template<class T>
DoubleList<T>::DoubleList(const DoubleList<T>& other) {
    dHead = nullptr;
    dNode<T> *nodePtr = other.head;
    int listSize = other.size();
    int i = 0;
    while(nodePtr != nullptr)
    {
        insert(i, nodePtr.data);
        nodePtr = nodePtr->next;
        i++;
    }
}

/*assignment operator*/
template<class T>
DoubleList<T>& DoubleList<T>::operator=(const DoubleList<T>& other) {
    //head = nullptr;
    int listSize = other.size();
    int i = 0;
    /*be wary of self assignment*/
    if(&other != this)
    {
        /*free up memory*/
        dNode<T>* temp = dhead;
        while(temp->next != nullptr)
        {
            dhead = head->next;
            delete temp;
            temp = dhead;
        }
        /*create a copy of other*/
        temp = other.dhead;
        for(i; i < listSize; i++)
        {
            insert(i, temp->data);
        }

    }
    return *this;

}

/*clone operator*/
template<class T>
DoubleList<T>* DoubleList<T>::clone() {
    /*create and return a deep copy of the linked list*/
    DoubleList<T> *newList;
    //newList = new LinkedList<T>(*this);
    for(int i = 0; i < *this->size(); i++)
    {
        newList.insert(i, *this->get(i));
    }
    return newList;
}

/*destructor*/
template<class T>
DoubleList<T>::~DoubleList(){
    dNode<T> *nodePtr;
    dNode<T> *nextNode;

    nodePtr = dhead;
    while(nodePtr != nullptr)
    {
        /*save a pointer to the next node*/
        nextNode = nodePtr -> next;
        /*delete current node*/
        delete nodePtr;
        nodePtr = nextNode;
    }
}

/*insert function*/
template<class T>
void DoubleList<T>::insert(int index, T data) {
    int i = 0;
    int listSize = size();
    dNode<T> *nodePtr;
    //Node *nextNode;
    dNode<T> *newNode;
    dNode<T> *prevNode = nullptr;

    if(index < 0 || index > listSize){
        throw "Invalid index used";
    }

    newNode = new Node;
    newNode->data = element;
    else if(isEmpty())
    {
        head = newNode;
        newNode->next = nullptr;
    }
    else
    {
        nodePtr = dhead;
        prevNode = nullptr;
        /*element is the first in the linked list*/
        if(index == 0)
        {
            dhead = newNode;
            newNode->next = nodePtr;
            return;
        }
        /*element is the last in the linked list*/
        else if(index == (listSize-1))
        {
            while(nodePtr->next)
            {
                nodePtr = nodePtr ->next;
            }
            nodePtr->next = newNode;
            return;
        }
        /*Insert after previous node*/
        else
        {
            while(nodePtr != nullptr)
                {
                    if(index == i)
                    {
                        prevNode->next = newNode;
                        newNode->next = nodePtr;
                        return;
                    }
                    i++;
                }
        }

}

/*remove function*/
template<class T>
T DoubleList<T>::remove(int index){
    int i = 0;
    int listSize = size();
    dNode<T> *nodePtr;
    dNode<T> *prevNode;
    dNode<T> *nodeFound;

    if(isEmpty())
        throw "List is Empty";
    else if(index < 0 || index > listSize)
        throw "Invalid index used";
    else
    {
        nodePtr = dhead;
        while(nodePtr != nullptr)
        {
            if(index == i){
                nodeFound = nodePtr;
                //nodePtr = nodePtr->next;
                //delete nodePtr;
            }
            //prevNode = nodePtr;
            nodePtr = nodePtr->next;
            i++;
        }
        if(nodePtr)
        {
            prevNode->next = nodePtr->next;
            delete nodePtr;
        }
    }
return nodeFound;
}

/*get function*/
template<class T>
T DoubleList<T>::get(int index) const {

    dNode<T> *nodePtr;
    //dNode<T> *prevNode;
    //dNode<T> *nodeFound;
    int listSize = size();
    int i = 0;
    nodePtr = dhead;
    if(isEmpty())
        throw "List is empty";
    else if(index < 0 || index > listSize)
        throw "Invalid Index";
    else
    {
        while(nodePtr != nullptr)
        {
            if(index == i){
                //nodeFound = nodePtr;
                return nodePtr;
            }

            //prevNode = nodePtr;
            nodePtr = nodePtr->next;
            i++;
        }
    }
    //return nodeFound;


}

/*overloaded [] operator*/
template <class T>
T DoubleList<T>::operator[](int index){
    return get(index);
}

/*isEmpty function*/
template<class T>
bool DoubleList<T>::isEmpty() {
    bool status;
    if(!dhead)
        status = true;
    else
        status = false;

    return status;

}

/*clear function*/
template<class T>
void DoubleList<T>::clear() {
    /*set up in a similar way to the destructor*/
    dNode<T> *nodePtr;
    dNode<T> *nextNode;

    nodePtr = dhead;
    while(nodePtr != nullptr)
    {
        /*save a pointer to the next node*/
        nextNode = nodePtr -> next;
        /*delete current node*/
        delete nodePtr;
        nodePtr = nextNode;
    }
    dhead = nullptr;

}

/*getHead function*/
template<class T>
dNode<T>* DoubleList<T>::getHead() const{
    if(isEmpty())
        throw "List is Empty";
    else
        return dhead;
}

/*print function*/
template<class T>
ostream& DoubleList<T>::print(ostream& os){
    return os;

}

/*size function*/
template<class T>
int DoubleList<T>::size() const{
    int counter = 0;
    dNode<T>* nodePtr = dhead;

    while(nodePtr != nullptr)
    {
        ++counter;
        nodePtr = nodePtr->next;
    }
    return counter;

}

/*overloaded + operator*/
template <class T>
DoubleList<T>& DoubleList<T>::operator+(const DoubleList<T>& other){

}
