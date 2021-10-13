//Provide the implementation for the LinkedList class in this file.
#include "linkedList.h"
#include "node.h"

using namespace std;
/*overloaded stream operator*/
template<class T>
ostream& operator<<(ostream& os, LinkedList<T>& ll) {
    int listSize = ll.size();
    if(ll.isEmpty())
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

/* Constructor */
template<class T>
LinkedList<T>::LinkedList(){
    head = NULL;
}

/*Copy Constructor*/
template<class T>
LinkedList<T>::LinkedList(const LinkedList<T>& other){
   /* head = nullptr;
    Node<T> dummy;
    Node<T> *obj = other.head;
    Node<T> *nodePtr = &dummy;

    for(;obj;obj=obj->next)
    {
        nodePtr->next = new Node<T>(obj->data);
        nodePtr = nodePtr->next;
    }
    head = dummy.next;*/
    Node<T> *nodePtr = other.head;
    int listSize = other.size();
    int i = 0;
    while(nodePtr != NULL)
    {
        insert(i, nodePtr.data);
        nodePtr = nodePtr->next;
        i++;
    }
}
/*overloaded assignment operator*/
template<class T>
LinkedList<T>& LinkedList<T>::operator=(const LinkedList<T>& other){
    //head = nullptr;
    int listSize = other.size();
    int i = 0;
    /*be wary of self assignment*/
    if(&other != this)
    {
        /*free up memory*/
        Node<T>* temp = head;
        while(temp->next != NULL)
        {
            head = head->next;
            delete temp;
            temp = head;
        }
        /*create a copy of other*/
        temp = other.head;
        for(i; i < listSize; i++)
        {
            insert(i, temp->data);
        }

    }
    return *this;
}

/*clone function*/
template<class T>
LinkedList<T>* LinkedList<T>::clone() {
    /*create and return a deep copy of the linked list*/
    LinkedList<T> *newList;
    //newList = new LinkedList<T>(*this);
    for(int i = 0; i < *this->size(); i++)
    {
        newList.insert(i, *this->get());
    }
    return newList;
}

/*Destructor*/
template<class T>
LinkedList<T>::~LinkedList(){
    Node<T> *nodePtr;
    Node<T> *nextNode;

    nodePtr = head;
    while(nodePtr != NULL)
    {
        /*save a pointer to the next node*/
        nextNode = nodePtr -> next;
        /*delete current node*/
        delete nodePtr;
        nodePtr = nextNode;
    }

}

/*Insert Function*/
template<class T>
void LinkedList<T>::insert(int index, T element){
    int i = 0;
    int listSize = size();
    Node<T> *nodePtr;
    //Node *nextNode;
    Node<T> *newNode;
    Node<T> *prevNode = NULL;

    if(index < 0 || index > listSize){
        throw "Invalid index used";
    }

    newNode = new Node<T>;
    newNode->data = element;
    if(isEmpty())
    {
        head = newNode;
        newNode->next = NULL;
    }
    else
    {
        nodePtr = head;
        prevNode = NULL;
        /*element is the first in the linked list*/
        if(index == 0)
        {
            head = newNode;
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
            while(nodePtr != NULL)
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
}

/*Remove function*/
template<class T>
T LinkedList<T>::remove(int index){
    int i = 0;
    int listSize = size();
    Node<T> *nodePtr;
    Node<T> *prevNode;
    Node<T> *nodeFound;

    if(isEmpty())
        throw "List is Empty";
    else if(index < 0 || index > listSize)
        throw "Invalid index used";
    else
    {
        nodePtr = head;
        while(nodePtr != NULL)
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

/*Get Function*/
template<class T>
T LinkedList<T>::get(int index) const {
    Node<T> *nodePtr;//traverse through the linked list
    //Node<T> *prevNode;
    //Node<T> *nodeFound;
    int listSize = size();
    int i = 0;
    nodePtr = head;
    if(isEmpty())
        throw "List is empty";
    else if(index < 0 || index > listSize)
        throw "Invalid Index";
    else
    {
        while(nodePtr != NULL)
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
}

/*isEmpty function*/
template<class T>
bool LinkedList<T>::isEmpty(){
    bool status;
    if(!head)
        status = true;
    else
        status = false;

    return status;
}

/*clear function*/
template<class T>
void LinkedList<T>::clear(){
    /*set up in a similar way to the destructor*/
    Node<T> *nodePtr;
    Node<T> *nextNode;

    nodePtr = head;
    while(nodePtr != NULL)
    {
        /*save a pointer to the next node*/
        nextNode = nodePtr -> next;
        /*delete current node*/
        delete nodePtr;
        nodePtr = nextNode;
    }
    head = NULL;
}

/*getLeader Function*/
template<class T>
Node<T>* LinkedList<T>::getLeader() const{
    if(isEmpty())
        throw "List is Empty";
    else
        return head;
}

/*print Function*/
template<class T>
ostream& LinkedList<T>::print(ostream& os){
    return os;

}

/*size function*/
template<class T>
int LinkedList<T>::size() const {
    int counter = 0;
    Node<T>* nodePtr = head;

    while(nodePtr != NULL)
    {
        ++counter;
        nodePtr = nodePtr->next;
    }
    return counter;
}

/*overloaded [] operator*/
template<class T>
T LinkedList<T>::operator[](int index){
    return get(index);
}

/*overloaded + operator*/
template<class T>
LinkedList<T>& LinkedList<T>::operator+(const LinkedList<T>& other){
    int listSize = other.size();
    int counter = 0;
    int newSize = other.size() + this->size();
    LinkedList<T>* newList;
    for(int i = other.size() - 1; i < newSize; i++)
    {
        insert(i, other.get(counter));
        counter++;
    }
    return newList;
}
