CREATE TABLE EMP
(
  EMPNO     NUMBER(4),
  ENAME     VARCHAR2(10 BYTE),
  JOB       VARCHAR2(9 BYTE),
  MGR       NUMBER(4),
  HIREDATE  DATE,
  SAL       NUMBER(7,2),
  COMM      NUMBER(7,2),
  DEPTNO    NUMBER(2),
  ACCOUNT   CLOB
)
LOGGING 
NOCOMPRESS 
NOCACHE
NOPARALLEL
MONITORING;

COMMENT ON COLUMN EMP.EMPNO IS '社員番号';

COMMENT ON COLUMN EMP.ENAME IS '氏名';

COMMENT ON COLUMN EMP.JOB IS '職業';

COMMENT ON COLUMN EMP.MGR IS '直属上司';

COMMENT ON COLUMN EMP.HIREDATE IS '入社日';

COMMENT ON COLUMN EMP.SAL IS '給与';

COMMENT ON COLUMN EMP.COMM IS '賞与';

COMMENT ON COLUMN EMP.DEPTNO IS '部署番号';


CREATE TABLE DEPT
(
  DEPTNO  NUMBER(2),
  DNAME   VARCHAR2(14 BYTE),
  LOC     VARCHAR2(13 BYTE)
)
LOGGING 
NOCOMPRESS 
NOCACHE
NOPARALLEL
MONITORING;


CREATE TABLE EMP_FILE
(
  EMPNO      VARCHAR2(4 BYTE),
  FILE_NAME  VARCHAR2(50 BYTE),
  FILE_DATA  BLOB
)
LOGGING 
NOCOMPRESS 
NOCACHE
NOPARALLEL
MONITORING;


CREATE UNIQUE INDEX EMP_FILE_PK ON EMP_FILE
(EMPNO)
LOGGING
NOPARALLEL;


CREATE UNIQUE INDEX PK_DEPT ON DEPT
(DEPTNO)
LOGGING
NOPARALLEL;


CREATE UNIQUE INDEX PK_EMP ON EMP
(EMPNO)
LOGGING
NOPARALLEL;


ALTER TABLE EMP ADD (
  CONSTRAINT PK_EMP
 PRIMARY KEY
 (EMPNO));

ALTER TABLE DEPT ADD (
  CONSTRAINT PK_DEPT
 PRIMARY KEY
 (DEPTNO));

ALTER TABLE EMP_FILE ADD (
  CONSTRAINT EMP_FILE_PK
 PRIMARY KEY
 (EMPNO));



Insert into DEPT
   (DEPTNO, DNAME, LOC)
 Values
   (10, 'ACCOUNTING', 'NEW YORK');
Insert into DEPT
   (DEPTNO, DNAME, LOC)
 Values
   (20, 'RESEARCH', 'DALLAS');
Insert into DEPT
   (DEPTNO, DNAME, LOC)
 Values
   (30, 'SALES', 'CHICAGO');
Insert into DEPT
   (DEPTNO, DNAME, LOC)
 Values
   (40, 'OPERATIONS', 'BOSTON');



Insert into EMP
   (EMPNO, ENAME, JOB, MGR, HIREDATE, SAL, DEPTNO)
 Values
(1111, 'Honggildong', '総務', 22, TO_DATE('01/02/2012 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 5555, 10);
Insert into EMP
   (EMPNO, ENAME, JOB, MGR, HIREDATE, SAL, DEPTNO)
 Values
   (7369, 'SMITH', 'CLERK', 7902, TO_DATE('12/17/1980 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 4157.28, 30);
Insert into EMP
   (EMPNO, ENAME, JOB, MGR, HIREDATE, SAL, DEPTNO)
 Values
   (7499, 'ALLEN', 'SALESMAN', 7698, TO_DATE('02/20/1981 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 1607, 30);
Insert into EMP
   (EMPNO, ENAME, JOB, MGR, HIREDATE, SAL, COMM, DEPTNO)
 Values
   (7521, 'WARD', 'SALESMAN', 7698, TO_DATE('02/22/1981 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 1250, 500, 30);
Insert into EMP
   (EMPNO, ENAME, JOB, MGR, HIREDATE, DEPTNO)
 Values
   (7566, 'JONES', 'MANAGER', 7839, TO_DATE('04/02/1981 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 20);
Insert into EMP
   (EMPNO, ENAME, JOB, MGR, HIREDATE, SAL, COMM, DEPTNO)
 Values
   (7654, 'MARTIN', 'SALESMAN', 7698, TO_DATE('09/28/1981 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 1250, 1400, 10);
Insert into EMP
   (EMPNO, ENAME, JOB, MGR, HIREDATE, SAL, DEPTNO)
 Values
   (7698, 'BLAKE', 'MANAGER', 7839, TO_DATE('05/01/1981 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 2850, 30);
Insert into EMP
   (EMPNO, ENAME, JOB, MGR, HIREDATE, SAL, DEPTNO)
 Values
   (7782, 'CLARK', 'MANAGER', 7839, TO_DATE('06/09/1981 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 2450, 10);
Insert into EMP
   (EMPNO, ENAME, JOB, MGR, HIREDATE, SAL, DEPTNO)
 Values
   (7788, 'SCOTT', 'ANALYST', 7566, TO_DATE('04/19/1987 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 3000, 20);
Insert into EMP
   (EMPNO, ENAME, JOB, HIREDATE, SAL, DEPTNO)
 Values
   (7839, 'KING', 'PRESIDENT', TO_DATE('11/17/1981 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 5000, 10);
Insert into EMP
   (EMPNO, ENAME, JOB, MGR, HIREDATE, SAL, COMM, DEPTNO)
 Values
   (7844, 'TURNER', 'SALESMAN', 7698, TO_DATE('09/08/1981 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 1500, 0, 30);
Insert into EMP
   (EMPNO, ENAME, JOB, MGR, HIREDATE, SAL, DEPTNO)
 Values
   (7876, 'ADAMS', 'CLERK', 7788, TO_DATE('05/23/1987 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 1100, 20);
Insert into EMP
   (EMPNO, ENAME, JOB, MGR, HIREDATE, SAL, DEPTNO)
 Values
   (7900, 'JAMES', 'CLERK', 7698, TO_DATE('12/03/1981 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 950, 30);
Insert into EMP
   (EMPNO, ENAME, JOB, MGR, HIREDATE, SAL, DEPTNO)
 Values
   (7902, 'FORD', 'ANALYST', 7566, TO_DATE('12/03/1981 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 3000, 20);
Insert into EMP
   (EMPNO, ENAME, JOB, MGR, HIREDATE, SAL, DEPTNO)
 Values
   (7934, 'MILLER', 'CLERK', 7782, TO_DATE('01/23/1982 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 1300, 10);

commit;

