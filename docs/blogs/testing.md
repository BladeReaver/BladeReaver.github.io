So I had to write a post because today one of my coworkers wrote in a group chat "unit tests are for people who don't have confidence in the code". This stirred quite a few emotions in me, mainly because I keep having to remind my team that we need to have more unit tests (we are currently just below 50%).

Before I could become upset and immediatly tell my coworker what I thought about his comment I let it go and tried to think why my coworker made that remark and if I on some level agree with his statement.


Let's first begin why we write unit tests. This powerful tool is used to quickly test individual functionality in isulation, this usually is confined to a single class in Java. We write it to verify what is written in the functional code works as expected to what is designed. We have other types of tests, here I would like to talk about the testing pyramid again, which should also be created for your application landscape, but because unit tests are super fast this should contain the bulk of testing.

If this is it then I would understand the comment made by my coworker, because I know what I created and tested is working as expected. It has also been verified by a tester and he/she has also confirmed that it works, but it doesn't end there. 

The second important reason to write unit tests; We make sure that software keeps working even if someone else makes changes. It is all fine and dandy that the software you created is working but what happens when in the future a different coworker makes a small change in another part of the application? It could be that the change does not trigger a build error but it could significantly change the behaviour of the application. To make sure that everything keeps working (and to not waste the precious time of the testers) we should verify that the functionality of the application is not changed in a way that it is different to what is expected.

So as a conclusion to a long and winded rant (...uh I mean proffesional blog post), we should definitly keep writing unit tests and have enought of them so that we can confidently state that our application is working as designed.
