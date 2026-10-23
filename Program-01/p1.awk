BEGIN{
dcount=0;
rcount=0;
}
{
event=$1;
if(event=="d")
{
dcount++;
}
if(event=="r")
{
rcount++;
}
}
END{
printf("Packets dropped=%d\n",dcount);
printf("Packets received=%d\n",rcount);
}
