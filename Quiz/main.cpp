#include <iostream>
#include <vector>
#include <conio.h>
#include<algorithm>
using namespace std;

struct player{
	int score;
	string name;
};

bool sortDescending(player a, player b){
	if(a.score > b.score)
		return true;
	else 
		return false;
}
int main() {

    vector<player> players;

    loop:
    cout << "========================================================================================================================" <<endl;
    cout << "                                              Welcome to ComQuiz!" <<endl;
    cout << "========================================================================================================================" <<endl;
   
    cout << "1. Start Quiz" << endl;
    cout << "2. View ranking" << endl;
    cout << "3. View the players" << endl;
    cout << "4. Remove player" << endl;
    cout << "5. Search a player" << endl;
    cout << "6. Exit" << endl;
    
    int choice;
    cout << "Enter your choice: (1,2,3,4,5): ";
    cin >> choice;
    cin.ignore();

    system("cls");

    switch (choice) {
	    case 1: {
	    	//Create player object
	        player newPlayer = player();
			
			//Welcome user
			cout << "Welcome to the quiz!" << endl;
	        cout << "Please enter your name: ";
	        cin >> newPlayer.name;
	        cout << endl;
	        
	        //Indicate usage instruction
	        cout << "Quiz instructions:" << endl;
	        cout << "1. Answer each question by entering the letter of the correct choice." << endl;
	        cout << "2. There are two questions in the quiz." << endl;
	        cout << "3. You will receive 1 point for each correct answer." << endl;
	        cout << endl;
	        system("pause");
	        system("cls");
	        
	        //Declare once and use multiple times to save memory
	        int score = 0;
	        string answer = "";
	        
	        //"Quiz Header"
			cout << "Quiz questions:" << endl;
			
			//Question 1
	        cout << "1. What is the capital of France?" << endl;
	        cout << "a. Paris" << endl;
	        cout << "b. London" << endl;
	        cout << "c. New York" << endl;
	        cout << "Enter choice: ";
			answer = "";
	        cin >> answer;
	        if (answer == "a")
	            score++;
	
			//Question 2
	        cout << "2. What is 5 + 5?" << endl;
	        cout << "a. 1" << endl;
	        cout << "b. 10" << endl;
	        cout << "c. 4" << endl;
	        cout << "Enter choice: ";
	        answer = "";
	        cin >> answer;
	        if (answer == "b")
	        	score++;
	        	
	        //Question 3
	        cout << "3. What is 2 + 2?" << endl;
	        cout << "a. 1" << endl;
	        cout << "b. 3" << endl;
	        cout << "c. 4" << endl;
	        cout << "Enter choice: ";
	        answer = "";
	        cin >> answer;
	        if (answer == "c")
	            score++;
	            
	        //Add results of newPlayer to vector players
	        newPlayer.score = score;
	        players.push_back(newPlayer);
	
			//Congratulatory message
	        cout << "Congratulations, " << newPlayer.name << "! Your score is: " << newPlayer.score << endl;
	        system("pause");
	        system("cls");
	        goto loop;
	        break;
	    }
	    case 2: {
	        // Display the ranking of users
	        cout << "Ranking:" << endl;
	    
	    	//Create tempPlayer vector as a copy of players
	    	vector<player> tempPlayer = players;
	    	
	    	/**
	    	Note for previous solution attempts:
	    	
	    	Due to the use of "vector a = vec;" where vec is the original vector
	    	is just a reference of vec to a and not an actual copy of vec to a.
	    	Hence the erase() won't work as found in other lines that use "player.erase()".
	    	*/
	    	
	    	//Sort tempPlayer vector by score
	    	sort(tempPlayer.begin(), tempPlayer.end(), sortDescending);
	    	
	    	//Print tempPlayer
	    	for(int i = 0; i < tempPlayer.size(); i++)
	    		cout << tempPlayer[i].name << " = " << tempPlayer[i].score << endl;
	        
			system("pause");
	        system("cls");
	        goto loop;
	        break;
	    }
	    case 3: {
	        // Display the players 
			if (players.size() == 0) { //No players
		   		cout << "No players have taken the quiz yet." << endl;
	    	}
		    else { //Has players
			    cout << "List of players who have taken the quiz:" << endl;
			    for (int i = 0; i < players.size(); i++) {
			    	cout << i + 1 << ". " << players[i].name << endl;
			    }
		    }
	        system("pause");
	        system("cls");
	        goto loop;
	        break;
	    }
	    case 4: {
		    //Check size and return if no players found
			if(players.size() == 0){
		    	cout << "No players have taken the quiz yet." << endl;
		    	system("pause");
		        system("cls");
		        goto loop;
				break;
			}
			
			//There are players found
			cout << "List of players who have taken the quiz:" << endl;
		    for (int i = 0; i < players.size(); i++)
		    	cout << i + 1 << ". " << players[i].name << endl;
			cout << endl;
			
			//Ask for player name to remove
		    cout << "Enter the name of the player you want to remove: ";
		    string name;
		    cin >> name;
		
			//Find player by index
		    int index = -1;
		    for (int i = 0; i < players.size(); i++) {
		        if (players[i].name == name) {
		            index = i;
		            break;
		        }
		    }
		
			//Remove and tell user
		    if (index != -1) {
		        players.erase(players.begin() + index);
		        cout << "Player removed successfully." << endl;
		    } else {
		        cout << "Player not found." << endl;
		    }
	        system("pause");
	        system("cls");
	        goto loop;
	        break;
	    }
	    case 5: {
	    cout << "Enter the name of the player you want to search: ";
	    string searchName;
	    cin >> searchName;
	    bool found = false;
	    for (int i = 0; i < players.size(); i++) {
	        if (players[i].name == searchName) {
	            cout << "Player found!" << endl;
	            cout << "Name: " << players[i].name << endl;
	            cout << "Score: " << players[i].score << endl;
	            found = true;
	            break;
	        }
	    }
	    if (!found) {
	        cout << "Player not found!" << endl;
	    }
	        system("pause");
	        system("cls");
	        goto loop;
	        break;
	    
	    }
    	case 6:
    		exit(0);
	    default:
	    	cout << "Invalid choice. Please enter 1, 2, 3, 4 or 5." << endl;
	    	break;
    }


    getch();
    return 0;
}
