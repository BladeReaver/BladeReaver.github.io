# CI/CD Pipelines

So CI/CD pipelines, is it just a hype, a fancy buzzword people throw around, or is it actually adding value? **"YES"** 
without a doubt having a good CI/CD pipeline is an added benefit for everyone. But what is a good pipeline
and when can you actually call your pipeline a CI/CD pipeline? Let's take a further look.

The examples that I have seen which are being called a CI/CD pipeline fall into three categories, and 
I would qualify them differently, also I would state each one further in the list is a more mature
version of the previous one.
- Deployment pipeline
- CI/CD(eployment) pipeline
- CI/CD(elivery) pipeline

!!! Following is purely my vision about pipelines and CI/CD, if you have a different vision it could be correct or even
    better than mine. If you want to start a discussion you can contact me, I'm always open for good discussions.

### Deployment pipeline

This is a pipeline which would start on a push to a specific branch (develop or master), and would do a few steps in 
succession usually compiling, unit tests, creating deployable artifact, and deploy on a (test) machine. 

![Deployment pipeline](build-pipeline.png)

Is this a good pipeline? Well it's a start and adds benefit for developers, but is it an actual CI/CD pipeline? In that 
case I would have to say no. It is a nice basis, but before it can add value for other people it would need something 
more.

### CI/CD(eployment) pipeline

This is a pipeline which would start on a push to a specific branch or any branch and would do a few addition steps 
compared to a 'regular' deployment pipeline. All steps could then be compiling, unit tests and other 'local' tests, creating 
deployable artifact, deploy on a (test) machine, and test if the deployment was successful using an automated test. When 
you want to deploy on a machine further in the DTAP environment, it usually requires manual steps to be taken or new pipelines 
to be started manually. Alternatively it could already deploy on an ACCeptance machine so that manual tests can be started.

![CI/CD(eployment)](cicdeployment.PNG)

### CI/CD(elivery) pipeline

This is a pipeline which would start on a push to any branch and has different rules based on the type of branch, it would 
perform several steps in succession and requires no, or hardly any manual intervention. A complete pipeline would usually 
do compiling, unit tests and other 'local' tests, creating a deployable artifact, deploy on a test machine, test deployment 
with automated tests, deploy on an acceptance machine, do automated regression tests, prepares a production deployment
which can be manually started when required. 

![CI/CD(elivery)](cicdelivery.PNG)

## Now what?

So after reading all that I assume that everyone wants to implement a full CI/CD(delivery) pipeline, however this is not
as simple as creating a pipeline in your favourite tool (like Jenkins, Azure DevOps, etc) and you are done (wouldn't it
be nice if it was that simple?). My advice would be to identify what an ideal pipeline would look like for your organisation
and what is being done in each step.

Next up would be to prepare any automated tests, and to scope them to the necessary functionality. (see an upcoming blog 
about testing). 

When that is done you start simple and add a small step one by one, don't try to set up a full pipeline from scratch.

----

If you want an example pipeline check out [JenkinsFile](Jenkinsfile).