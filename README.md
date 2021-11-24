### Candidate's comments:

The assignment was super fun!
I didn't test everything for the sake of time, but i have tested the VM and some other logic.
This way you can see my tests.
Ideally, everything should be tested across all layers.

I only used Hilt, LiveData and Mockk in pet projects, 
The alternative tech-stack i am using in my current position is Dagger2, RxJava(RxKotlin), Mockito.

I hope you enjoy reviewing it.
Looking forward for your feedback!!


# GameBasicsCodeAssignment
You will receive our order. Great that you want to put your time into this!

We think it's important that your code is readable, written in English, well structured and commented. Make sure that your code is testable, extensible and maintainable, not only for yourself but also for others to understand. Keep in mind the SOLID principles. Of course, it is also important that the functioning of the end result matches the specs.

Assignment: “Make a mini-simulator for a group stage consisting of 4 teams”

Create a mini simulator that allows you to simulate a pool consisting of 4 different teams.

Create a new application / website.
Simulate the matches and results of a group of 4 teams. There are three rounds, so six games in total.
Keep in mind that each team has its own strength (which players play in the team, who scores, etc. is not so important in this assignment). We want to see this reflected in the final score (if we simulate the group very often, the better teams more often finish above the worse teams).
You can make the "match simulation" as simple or extensive as you want. Tip: we mainly care about the results and the group standings.
The graphical end result is at least the position of the group and what the mutual results were. Important: at the end we should be able to see which teams advance to the knockout stage, remember the sorting:

1. Points
2. Goal difference
3. Goals for
4. Goals against
5. Mutual result
