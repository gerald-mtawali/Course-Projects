#include <iostream>

using namespace std;

int main()
{
  //Write code to test your implementation.


	LinkedList<char> list1;
	char value = 'a';

	cout << "-----------insert------------" << endl;
	try{
		list1.insert(0,value);
		value++;
		cout << list1 << endl;
	}
	catch (const char * s){
		cout << "ERROR: " << s << endl;
	}
	cout << "-----copy constructor---------------" << endl;

	LinkedList<char>* list2 = new LinkedList<char>(list1);
	cout << *list2 << endl;
	cout << "-----------get------------" << endl;

	try{
		cout << "index: 4" << list1.get(4) << list1 << endl;

	}
	catch (const char * s){
		cout << "ERROR: " << s << endl;
	}

	cout << "-----------operator[]------------" << endl;
	//cout << list1 << endl;
	try{
		cout << "index: 2" << list1[-1] << endl;

	}
	catch (const char * s){
		cout << "ERROR: " << s << endl;
	}

	cout << "-----------remove------------" << endl;
	try{

		cout << list1.remove(1) << " -> " << list1 << endl;
	}
	catch (const char * s){
		cout << "ERROR: " << s << endl;
	}

	cout << "-----------operator=------------" << endl;
	list1 = *list2;

	cout << list1 << endl;

	cout << "-----------operator+------------" << endl;
	LinkedList<char> list3 = list1 + *list2;

	cout << list3 << endl;
  return 0;
  /////////////////////////////////////////////////////////
}
