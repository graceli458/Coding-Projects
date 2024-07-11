
The objective of this homework is to build a hand gesture classifier for sign language. We will be using Google Colaboratory to train our model (set up instructions at the end). The dataset will be in the form of csv files, where each row represents one image and its true label.
We have provided the skeleton code to read in the training and testing datasets. Before you begin coding, go through the provided code and try to figure out what each function is responsible for. Most of the functionalities are implemented in the SignLanguage class. Below are brief descriptions of each function and what is expected of you.


• create model(self): You will generate a keras sequential mode here. Make sure to set the self.model variable with your compiled sequential model.
• prepare data(self, images, labels): Mainly this splits the data into training and validation sets. You may choose to normalize or downsample your data here, as well.
• train(self, batch size, epochs, verbose): This method invokes the training of the model. Make sure to return the generated history object. Your model will be trained for a max of 50 epochs during grading. Make sure you are using the input parameters (batch size, epochs, verbose)
• predict(self, data): This method will be invoked with the test images. Make sure to downsample/resize the test images the same way as the training images, and return a list of predictions.
• visualize data(self, data): Nothing to do here. This is solely to help you visualize the type of data you are dealing with.
• visualize accuracy(self, history): Nothing to do here. It plots out the accuracy improvement over training time.
