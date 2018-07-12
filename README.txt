Prerequisites:
1. Java Development Kit - JDK 1.6 or above
2. Java Media Framework - JMF 2.1.1.e

Instructions:
To use personalized Movie Summarizer, you need metadata about the movie.

Movie video file should be in .mpeg format which is supported by JMF.

Sample features are given in \features directory (for the movie Rush Hour).

Features are stored in .csv files (Comma Separated Value)
	1. Actor.csv contain actors name followed by their appearances in shots, which are calculated using a face recognition system.
	2. Concept.csv contain confidence scores of 19 semantic concepts for each shot calculated using semantic concept detectors.
	3. Event.csv contain manually annotated confidence scores of 4 movie events and 2 automatically calculated scores of events for each shot.
	4. Scene.csv contain number of scenes in first line follwed by the shot numbers of each scene. Each line contain shots of each scene.
	5. Shot.csv contain total number of shots in the first line followed by shot details, which are shot number, starting time (in secs), ending time (in secs), duration (in secs).
	6. Transcripts.csv contain transcripts of each shot.

Compile and Execute PMVS.java