jetstorm
========

An Eclipse tool to apply several Storm design patterns, creating an entire storm topology implementations (ex business logic)

Input to the tool is a simple XML model listing the spouts, the bolts and the streams that connect them.

The tool, implemented as an Eclipse-based JET transform, applies the following patterns:

- A maven-enabled Java project for easy builds and dependency management
- A fully implemented topology class that can submit itself either to a LocalCluster or Cluster  
- Bolt and Spout classes with business logic factored into logic-only classes for separation of concerns and easier collaboration by developers with different skill sets
- Beans that fully encapsulate understanding of the streams (field name, type and order) that provide higher quality, lower risk and easier impact analysis for changes to stream shape
- J-Unit test classes for each spout, bolt and bean for code coverage.
- Specific test cases for common development errors (e.g. serialization, logic)
- Mocked storm classes (Collector, Tuple, FieldsDeclarer) for easier implementation of test cases that validate correct side effects for Spout and Bolt logic
- An input file for graphiz that visualizes the complete topology for easier communication of the topology to other team members and non-technical stakeholders

There are well-defined places in the generated code where changes made after generation will be retained through subsequent generations.  This means that you can make several kinds of changes to your topology and regenerate the code, keeping any business logic already typed in. 
