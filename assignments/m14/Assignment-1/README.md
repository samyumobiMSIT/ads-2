Substring matches. Given a list of (short) strings, your goal is to support queries where the user looks up a string s and your job is to report back all strings in the list that contain s. 
Hint: if you only want prefix matches (where the strings have to start with s), use a TST as described in the text. To support substring matches, insert the suffixes of each word (e.g., string, tring, ring, ing, ng, g) into the TST. 

Input Format:
First line contains the prefix string s, where you need to print the strings that matches prefix(where the strings have to start with s).

Output Format:
Print all the strings that match with prefix matches in line by line