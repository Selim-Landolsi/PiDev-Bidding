<?php

namespace App\Controller;

use App\Entity\Bid;
use App\Entity\Produit;
use App\Form\BidType;
use App\Form\BidTypeF;
use App\Form\BidTypeM;
use App\Repository\BidRepository;
use App\Repository\ProduitRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Doctrine\ORM\EntityManagerInterface;
use Doctrine\ORM\Mapping\Id;

#[Route('/bid')]
class BidController extends AbstractController
{
    #[Route('/', name: 'app_bid_index', methods: ['GET'])]
    public function index(BidRepository $bidRepository): Response
    {
        return $this->render('bid/index.html.twig', [
            'bids' => $bidRepository->findAll(),
        ]);
    }

    #[Route('/new', name: 'app_bid_new', methods: ['GET', 'POST'])]
    public function new(Request $request, BidRepository $bidRepository, BidController $bidController): Response
    {
        $bid = new Bid();
        $form = $this->createForm(BidType::class, $bid);
        $form->handleRequest($request);
        if ($bidRepository->BuyoutCheck($bid->getProduit()->getId())){ 
            if($bidRepository->highestBid($bid->getProduit()->getId()) > $bid->getBidAmount()){
                
        if ($form->isSubmitted() && $form->isValid()) {
            $bidRepository->save($bid, true);

            return $this->redirectToRoute('app_bid_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->renderForm('bid/new.html.twig', [
            'bid' => $bid,
            'form' => $form,
        ]);}else{ return $this->render('Operation/error.html.twig',
            [
              'message'=>'errer'
            ]
            ); }}else{ return $this->render('Operation/error.html.twig',
                [
                  'message'=>'errer'
                ]
                ); }
    }
    
    #[Route('/{id}/new', name: 'bid_f', methods: ['GET', 'POST'])]
    public function bidF(Request $request, string $id, BidRepository $bidRepository, ProduitRepository $produitRepository): Response
    {   //$prodid = $request->query->get('id');
        $produit = $produitRepository->find($id);
        $bid = Bid::create()->setProduit($produit);
        $form = $this->createForm(BidTypeF::class, $bid);
        $form->handleRequest($request);
        if ($produitRepository->BuyoutCheck($bid->getProduit()->getId())){
            

        if ($form->isSubmitted() && $form->isValid()) {
            if ($bid->getBidAmount() > $produit->getPrixDebutEnchere()){
            if($bidRepository->highestBid($bid->getProduit()->getId()) < $bid->getBidAmount() ){
            $bidRepository->save($bid, true);

            return $this->redirectToRoute('app_home', [], Response::HTTP_SEE_OTHER);
            }else{ return $this->render('operation/error.html.twig',
            [
              'message'=>'Bid Higher:'.strval($bidRepository->highestBid($bid->getProduit()->getId()))
            ]
            ); }}
            else{ return $this->render('operation/error.html.twig',
                [
                  'message'=>'Bid Higher:'.strval($produit->getPrixDebutEnchere())
                ]
                ); }
        }

        return $this->renderForm('bid/newF.html.twig', [
            'bid' => $bid,
            'produit.id' => 'id',
            'form' => $form,
        ]);}else{ return $this->render('operation/error.html.twig',
            [
              'message'=>'Product bought out'
            ]
            ); }
    
    }

    #[Route('/{id}/buyout', name: 'bid_buyout', methods: ['GET', 'POST'])]
    public function bidB(Request $request, string $id, BidRepository $bidRepository, ProduitRepository $produitRepository): Response
    {   //$prodid = $request->query->get('id');
        $produit = $produitRepository->find($id);
        $bid = Bid::create()->setProduit($produit);
        $bid->setBidAmount($produit->getPrixBuyOut());
        $form = $this->createForm(BidTypeF::class, $bid);
        $form->handleRequest($request);
        if ($produitRepository->BuyoutCheck($bid->getProduit()->getId())){
            

        if ($form->isSubmitted() && $form->isValid()) {
            if ($bid->getBidAmount() > $produit->getPrixDebutEnchere()){
            if($bidRepository->highestBid($bid->getProduit()->getId()) < $bid->getBidAmount() ){
            $bidRepository->save($bid, true);

            return $this->redirectToRoute('app_home', [], Response::HTTP_SEE_OTHER);
            }else{ return $this->render('operation/error.html.twig',
            [
              'message'=>'Bid Higher:'.strval($bidRepository->highestBid($bid->getProduit()->getId()))
            ]
            ); }}
            else{ return $this->render('operation/error.html.twig',
                [
                  'message'=>'Bid Higher:'.strval($produit->getPrixDebutEnchere())
                ]
                ); }
        }

        return $this->renderForm('bid/newF.html.twig', [
            'bid' => $bid,
            'produit.id' => 'id',
            'form' => $form,
        ]);}else{ return $this->render('operation/error.html.twig',
            [
              'message'=>'Product bought out'
            ]
            ); }
    
    }

    #[Route('/{id}', name: 'app_bid_show', methods: ['GET'])]
    public function show(Bid $bid): Response
    {
        return $this->render('bid/show.html.twig', [
            'bid' => $bid,
        ]);
    }

    #[Route('/{id}/edit', name: 'app_bid_edit', methods: ['GET', 'POST'])]
    public function edit(Request $request, Bid $bid, BidRepository $bidRepository): Response
    {
        $form = $this->createForm(BidTypeM::class, $bid);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $bidRepository->save($bid, true);

            return $this->redirectToRoute('app_bid_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->renderForm('bid/edit.html.twig', [
            'bid' => $bid,
            'form' => $form,
        ]);
    }

    #[Route('/{id}', name: 'app_bid_delete', methods: ['POST'])]
    public function delete(Request $request, Bid $bid, BidRepository $bidRepository): Response
    {
        if ($this->isCsrfTokenValid('delete'.$bid->getId(), $request->request->get('_token'))) {
            $bidRepository->remove($bid, true);
        }

        return $this->redirectToRoute('app_bid_index', [], Response::HTTP_SEE_OTHER);
    }

    
    
  
}